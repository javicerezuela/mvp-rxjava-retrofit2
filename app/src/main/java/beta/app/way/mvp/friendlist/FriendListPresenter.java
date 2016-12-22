package beta.app.way.mvp.friendlist;

import android.support.annotation.NonNull;

import beta.app.way.apis.APIErrorHandler;
import beta.app.way.apis.APISubscriber;
import beta.app.way.entities.FriendListResponse;
import rx.Subscription;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public class FriendListPresenter implements FriendListPageContract.Presenter {
    private FriendListPageContract.View mView;
    private FriendListModel mModel;
    private Subscription mSubscription;
    private APIErrorHandler mApiErrorHandler;

    public FriendListPresenter(@NonNull FriendListPageContract.View view,
                               @NonNull FriendListModel model,
                               @NonNull Subscription subscription,
                               @NonNull APIErrorHandler apiErrorHandler) {
        mView = view;
        mModel = model;
        mSubscription = subscription;
        mApiErrorHandler = apiErrorHandler;
    }

    @Override
    public void init() {
        obtainDataFromAPI();
    }

    @Override
    public void tearDown() {
        mView = null;
        mModel = null;
        mSubscription = null;
        mApiErrorHandler = null;
    }

    @Override
    public void obtainDataFromAPI() {

        mSubscription = mModel.obtainDataFromAPI(new APISubscriber<FriendListResponse>(mApiErrorHandler) {
            @Override
            public void onError(@NonNull String message, @NonNull ErrorType errorType) {
                super.onError(message, errorType);
                mView.showProgress(false);
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.showProgress(false);
                mSubscription.unsubscribe();
            }

            @Override
            public void onNext(FriendListResponse value) {
                super.onNext(value);
                mView.updateListWithSource(value.getData().getFriends());
                mView.showProgress(false);
            }
        });
    }
}
