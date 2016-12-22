package beta.app.way.apis;

import beta.app.way.entities.FriendListResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Restful API
 *
 * Created by panjiyudasetya on 12/16/16.
 */

public interface API {
    public static final String PRODUCTION_URL = "http://production-url.com";
    public static final String STAGING_URL = "http://staging-url.com";
    public static final String APIARY_URL = "http://private-17499-way1.apiary-mock.com";

    @GET("friend/list")
    Observable<FriendListResponse> getFriendList();
}
