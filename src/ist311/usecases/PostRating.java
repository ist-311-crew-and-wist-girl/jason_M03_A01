package ist311.usecases;

// Import local packages here
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import ist311.utils.ConnectDB;

import org.javatuples.Pair;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Insert user review into business's specific MongoDB collection and return update from collection.
 * @author Jason C. Nucciarone
 *
 */
public class PostRating {
    private int user_id;
    private Pair<String, String> user_name;
    private int business_id;
    private String business_name;
    private double user_safety_rating;
    private List<String> tags;
    private String user_review;
    private MongoClient conn;
    private Timestamp timestamp;

    // Constructor - assume data is already collected from GUI
    public PostRating (int user_id, Pair<String, String> user_name, int business_id, String business_name, double user_safety_rating,
                List<String> tags, String user_review){
        this.user_id = user_id;
        this.user_name = user_name;
        this.business_id = business_id;
        this.business_name = business_name;
        this.user_safety_rating = user_safety_rating;
        this.tags = tags;
        this.user_review = user_review;
        this.conn = ConnectDB.getConnection();

        // Generate timestamp for document
        Calendar calendar = Calendar.getInstance();
        this.timestamp = new Timestamp(calendar.getTime().getTime());
    }

    public void updateCollection(){
        try {
            // Connect to BUSINESS_REVIEW database
            MongoClient mongoClient = getConn();
            MongoDatabase database = mongoClient.getDatabase("BUSINESS_REVIEW");

            // Grab collection
            MongoCollection<Document> table = database.getCollection(getBusiness_name());

            // Create Document and create Unique ID
            Document doc = new Document("_id", new ObjectId().toString());

            // Process the data into a BSON readable format
            Pair<String, String> temp = getUser_name();
            String full_name = temp.getValue0() + " " + temp.getValue1();
            List<String> temp_list = getTags();
            String temp_string = temp_list.toString();
            temp_string = temp_string.replace("[", "");
            temp_string = temp_string.replace("]", "");
            Timestamp temp_timestamp = getTimestamp();
            String temp_string_2 = temp_timestamp.toString();

            // Add data to Document
            doc.append("user_id", getUser_id());
            doc.append("user_name", full_name);
            doc.append("business_id", getBusiness_id());
            doc.append("business_name", getBusiness_name());
            doc.append("user_safety_rating", getUser_safety_rating());
            doc.append("tags", temp_string);
            doc.append("user_review", getUser_review());
            doc.append("timestamp", temp_string_2);

            // Add document to database
            table.insertOne(doc);

        } catch(Exception e){
            String error = "Failed to connect to MongoDB instance! Check if MongoDB is running!";
            System.out.println(error);
            throw e;
        }
    }

    // Setters and getters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Pair<String, String> getUser_name() {
        return user_name;
    }

    public void setUser_name(Pair<String, String> user_name) {
        this.user_name = user_name;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public double getUser_safety_rating() {
        return user_safety_rating;
    }

    public void setUser_safety_rating(double user_safety_rating) {
        this.user_safety_rating = user_safety_rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUser_review() {
        return user_review;
    }

    public void setUser_review(String user_review) {
        this.user_review = user_review;
    }

    public MongoClient getConn() {
        return conn;
    }

    public void setConn(MongoClient conn) {
        this.conn = conn;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
