package beta.app.way.mvp.friendlist;

import android.support.annotation.NonNull;

import java.util.List;

import beta.app.way.entities.Friend;
import beta.app.way.mvp.BasePresenter;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public interface FriendListPageContract {

    interface View {
        void showProgress(boolean show);
        void updateListWithSource(@NonNull List<Friend> friends);
    }

    interface Presenter extends BasePresenter {
        void obtainDataFromAPI();
    }
}
