package beta.app.way.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import beta.app.way.R;
import beta.app.way.entities.Friend;
import beta.app.way.utils.LogHelper;

/**
 * Created by panjiyudasetya on 12/19/16.
 */

public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Friend> mDataSource;
    private Activity mActivity;

    public FriendAdapter(@NonNull Activity activity, @NonNull List<Friend> dataSource) {
        mActivity = activity;
        mDataSource = dataSource;
    }

    public void setDataSource(@NonNull List<Friend> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_friend, parent, false);
        FriendViewHolder holder = new FriendViewHolder(mActivity, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Friend data = mDataSource.get(position);
        if (holder instanceof FriendViewHolder) {
            ((FriendViewHolder) holder).setData(data);
            ((FriendViewHolder) holder).ivSelectable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogHelper.showToast(mActivity, data.getName());
                }
            });
        }
    }
}
