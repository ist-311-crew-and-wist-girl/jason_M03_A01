package ist311.main;

// Import local packages here
import ist311.usecases.PostRating;
import ist311.usecases.SearchBusiness;

import org.bson.Document;
import org.javatuples.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * Main method to test that PostRating and SearchBusiness Use Cases work.
 * @author Jason C. Nucciarone
 *
 */
public class Main {
    public static void main(String[] args) {
        /*
          First, to test our ability to update the BUSINESS_REVIEW database.
          Try to add two entries to separate collections.

          Assumptions:
          We will be assuming that the user has already written their review
          and that we have retrieved the information from the user interface.
          We will also be assuming that the database and relevant collections
          are already defined.

          The following fields will have been completed:
            - user_id
            - user_name
            - business_id
            - business_name
            - user_safety_rating
            - tags
            - user_review
         */

        // Define assumptions
        int user_id_1 = 1001;
        Pair<String, String> user_name_1 = Pair.with("Kyle", "Montenucci");
        int business_id_1 = 2002;
        String business_name_1 = "MICHEALS_PIZZA_001";
        double user_safety_rating_1 = 7.25;
        List<String> tags_1 = new ArrayList<>();
        tags_1.add("Not clean");
        tags_1.add("Wearing masks wrong!");
        String user_review_1 = "Just bad staff. I usually love this place! Wear masks correctly next time please.";

        // Add to database
        PostRating rating_1 = new PostRating(user_id_1,
                user_name_1,
                business_id_1,
                business_name_1,
                user_safety_rating_1,
                tags_1,
                user_review_1);
        rating_1.updateCollection();
        ArrayList<Document> ratings_for_1 = rating_1.refresh();
        for (int i = 0; i < ratings_for_1.size(); i++) {
            System.out.println(ratings_for_1.get(i));
        }

        // Create another document
        int user_id_2 = 1002;
        Pair<String, String> user_name_2 = Pair.with("Sarah", "Conner");
        int business_id_2 = 2002;
        String business_name_2 = "MICHEALS_PIZZA_001";
        double user_safety_rating_2 = 5;
        List<String> tags_2 = new ArrayList<>();
        tags_2.add("Not clean");
        tags_2.add("No masks!");
        tags_2.add("More the 6 feet");
        String user_review_2 = "None of the kitchen staff were wearing masks, although, to give them credit they did " +
                "have all the customers spaced out more than 6 feet!";

        PostRating rating_2 = new PostRating(user_id_2,
                user_name_2,
                business_id_2,
                business_name_2,
                user_safety_rating_2,
                tags_2,
                user_review_2);
        rating_2.updateCollection();
        ArrayList<Document> ratings_for_2 = rating_2.refresh();
        for (int i = 0; i < ratings_for_2.size(); i++) {
            System.out.println(ratings_for_2.get(i));
        }

        // Create document in a different collection
        int user_id_3 = 1003;
        Pair<String, String> user_name_3 = Pair.with("Kaylie", "Arujo");
        int business_id_3 = 2003;
        String business_name_3 = "VARLAS_THRIFT_SHOP_001";
        double user_safety_rating_3 = 10;
        List<String> tags_3 = new ArrayList<>();
        tags_3.add("Free masks");
        tags_3.add("Free hand sanitizer");
        tags_3.add("Limit maximum occupancy");
        String user_review_3 = "Place was an absolute dream!. I felt safer here than in my own apartment!";

        PostRating rating_3 = new PostRating(user_id_3,
                user_name_3,
                business_id_3,
                business_name_3,
                user_safety_rating_3,
                tags_3,
                user_review_3);
        rating_3.updateCollection();
        ArrayList<Document> ratings_for_3 = rating_3.refresh();
        for (int i = 0; i < ratings_for_3.size(); i++) {
            System.out.println(ratings_for_3.get(i));
        }

        /*
          Second, we need to check if we are to search the BUSINESSES
          database to find a business as well as return that relevant
          info for the business.

          The assumption that we are making is that we already have the
          geojson data available for conducting the search. We are also
          assuming that the user has their devices geolocation API enabled.
         */

        // Locate first business
        Pair<Double, Double> coordinates_1 = Pair.with(125.6, 10.1);
        SearchBusiness business_1 = new SearchBusiness(coordinates_1);
        String business_location_1 = business_1.searchByGeoLocation();
        System.out.println(business_location_1);

        // Locate second business
        Pair<Double, Double> coordinates_2 = Pair.with(70.6, 12.1);
        SearchBusiness business_2 = new SearchBusiness(coordinates_2);
        String business_location_2 = business_2.searchByGeoLocation();
        System.out.println(business_location_2);

    }
}
