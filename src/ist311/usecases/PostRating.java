package ist311.usecases;

// Import local packages here
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import ist311.utils.ConnectDB;

import org.javatuples.Pair;
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
    private double user_safety_rating;
    private List<String> tags;
    private String user_review;

    // Constructor - assume data is already collected from GUI
    PostRating (int user_id, Pair<String, String> user_name, int business_id, double user_safety_rating,
                List<String> tags, String user_review){
        this.user_id = user_id;
        this.user_name = user_name;
        this.business_id = business_id;
        this.user_safety_rating = user_safety_rating;
        this.tags = tags;
        this.user_review = user_review;
    }
}
