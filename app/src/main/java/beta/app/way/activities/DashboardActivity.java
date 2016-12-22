package beta.app.way.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import beta.app.way.R;
import beta.app.way.adapters.FriendAdapter;
import beta.app.way.apis.APIErrorHandler;
import beta.app.way.core.BaseActivity;
import beta.app.way.entities.Friend;
import beta.app.way.mvp.friendlist.FriendListModel;
import beta.app.way.mvp.friendlist.FriendListPageContract;
import beta.app.way.mvp.friendlist.FriendListPresenter;
import butterknife.BindView;

/**
 * Created by panjiyudasetya on 12/16/16.
 */

public class DashboardActivity extends BaseActivity implements FriendListPageContract.View, FriendAdapter.FriendAdapterCallback {
    @BindView(R.id.progress)
    ProgressBar mProgressView;
    @BindView(R.id.recycler_view)
    RecyclerView mRvFriends;

    private FriendAdapter mAdapter;
    private Activity mActivity;
    private FriendListPresenter mPresenter;

    @Override
    public int withLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        mAdapter = new FriendAdapter(mActivity, this, new ArrayList<Friend>());
        mRvFriends.setLayoutManager(new GridLayoutManager(this, 2));
        mRvFriends.setAdapter(mAdapter);
        mPresenter = new FriendListPresenter(this,
                new FriendListModel(),
                mSubscription,
                new APIErrorHandler(mActivity));
    }

    @Override
    protected void init() {
        super.init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showProgress(true);
        mPresenter.obtainDataFromAPI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.tearDown();
    }

    @Override
    public void showProgress(boolean show) {
        if (show)
            mProgressView.setVisibility(View.VISIBLE);
        else
            mProgressView.setVisibility(View.GONE);
    }

    @Override
    public void updateListWithSource(@NonNull List<Friend> friends) {
        mAdapter.setDataSource(friends);
    }

    @Override
    public void onItemClick(@NonNull final View view, int position, @NonNull final Friend friend) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DetailActivity.launch(DashboardActivity.this, view, friend);
            }
        }, 300);
    }
}
