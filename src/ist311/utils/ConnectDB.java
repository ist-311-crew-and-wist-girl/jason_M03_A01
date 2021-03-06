package ist311.utils;

// Import necessary modules for connecting to MongoDB instance
import com.mongodb.*;

public class ConnectDB {
    public static MongoClient getConnection() {
        String mongo_path = "mongodb://localhost:27017";
        testConnection(mongo_path);
        return new MongoClient(new MongoClientURI(mongo_path));
    }

    private static void testConnection(String mongo_connection_uri){
        try {
            MongoClient db = new MongoClient(new MongoClientURI(mongo_connection_uri));

        } catch(Exception e){
            String error = "Failed to connect to MongoDB instance! Check if MongoDB is running!";
            System.out.println(error);
            throw e;
        }
    }
}
