package xyz.iiemyewrs.www.technica.appConfig;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import xyz.iiemyewrs.www.technica.instagram.InstaFeed;


public class APIClient {
    //private static DataInterface dataInterface = null;
    private static InstaFeedInterface instaFeedInterface = null;

    public static InstaFeedInterface getInstagram() {
        if (instaFeedInterface == null) {
            String URL_INSTA = "https://api.instagram.com";
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(URL_INSTA)
                    .build();

            instaFeedInterface = restAdapter.create(InstaFeedInterface.class);
        }
        return instaFeedInterface;
    }

    public interface InstaFeedInterface {
        @GET("/v1/tags/terratechnica/media/recent?client_id=/*A valid client id here*/")
        void getFeed(Callback<InstaFeed> instaFeedCallback);
    }
}