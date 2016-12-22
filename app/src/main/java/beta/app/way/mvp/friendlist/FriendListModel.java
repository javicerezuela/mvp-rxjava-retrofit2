package beta.app.way.mvp.friendlist;

import android.support.annotation.NonNull;
import beta.app.way.apis.APIClient;
import beta.app.way.apis.APISubscriber;
import rx.Subscription;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public class FriendListModel {

    /**
     * Obtain data from api
     *
     * @param subscriber subscriber
     * @return Subscription
     */
    public Subscription obtainDataFromAPI(@NonNull APISubscriber subscriber) {
        return new APIClient().getFriends(subscriber);
    }
}
