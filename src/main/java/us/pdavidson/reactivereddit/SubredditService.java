package us.pdavidson.reactivereddit;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

import java.util.Date;

/**
 * Created by pete on 9/13/14.
 */
public class SubredditService implements ErrorHandler {

    private final SubredditResource subredditResource;

    public SubredditService() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://reddit.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setErrorHandler(this)
                .build();
        this.subredditResource = restAdapter.create(SubredditResource.class);
    }

    @Override
    public Throwable handleError(RetrofitError retrofitError) {
        System.err.println(retrofitError);
        return new RuntimeException(retrofitError);
    }

    interface SubredditResource {

        @GET("/r/{subreddit}/{type}.json")
        Observable<ListingBean> listSubreddit(@Path("subreddit")String subreddit, @Path("type") String type);
    }


    public Observable<ListingBean>listSubreddit(String subreddit, String type){
        return subredditResource.listSubreddit(subreddit, type);
    }

}
