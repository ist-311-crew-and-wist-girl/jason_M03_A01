package ist311.usecases;

// Import local packages here
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import ist311.utils.ConnectDB;
import org.javatuples.Pair;


/**
 * Query BUSINESSES database in various ways to find the correct business for a user.
 * For this assignment, searching by matching GeoJson coordinates.
 * @author Jason C. Nucciarone
 *
 */
public class SearchBusiness {
    private MongoClient conn;
    private Pair<Double, Double> coordinates;

    public SearchBusiness(Pair<Double, Double> coordinates){
        this.conn = ConnectDB.getConnection();
        this.coordinates = coordinates;
    }

    public String searchByGeoLocation(){
        // Connect to BUSINESSES database
        MongoClient mongoClient = getConn();
        MongoDatabase database = mongoClient.getDatabase("BUSINESSES");

        // Grab collection
        MongoCollection<Document> table = database.getCollection("BY_GEOLOCATION");

        BasicDBObject query = new BasicDBObject("geometry",
                new BasicDBObject("type", "Point").append("coordinates", coordinates.toArray()));
        // Locate business by geolocation data
        try (MongoCursor<Document> cursor = table.find(query).iterator()){
            if (cursor.hasNext()){
                // Get specific document
                Document doc = cursor.next();

                // Close connection to database
                mongoClient.close();
                return doc.get("properties", Document.class).getString("name");

            } else {
                mongoClient.close();
                return null;
            }
        }
    }

    // Setters and getters
    public MongoClient getConn() {
        return conn;
    }

    public void setConn(MongoClient conn) {
        this.conn = conn;
    }

    public Pair<Double, Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }
}
