package beta.app.way.apis;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by panjiyudasetya on 12/16/16.
 */

public class APIClient extends APIConfigurations {

    public APIClient() {
        super();
    }

    /**
     * Get Friends API Client
     *
     * @param subscriber subscriber
     * @return Subscription
     */
    public Subscription getFriends(APISubscriber subscriber) {
        return APIService.getFriendList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}