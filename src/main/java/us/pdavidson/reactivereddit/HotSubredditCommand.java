package us.pdavidson.reactivereddit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by pete on 9/13/14.
 */
public class HotSubredditCommand extends HystrixCommand<ListingBean> {

    private final String subreddit;

    public HotSubredditCommand(String subreddit){
//        super(HystrixCommandGroupKey.Factory.asKey("SubredditGroup"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SubredditGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationThreadTimeoutInMilliseconds(5000)));

        this.subreddit = subreddit;
    }

    @Override
    protected ListingBean run() throws Exception {

        SubredditService subredditService = new SubredditService();
        ListingBean hot = subredditService.listSubreddit(subreddit, "hot")
                .toBlocking()
                .first();
        System.out.println("Exiting Run");
        return hot;
    }
}
