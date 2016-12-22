package beta.app.way.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import beta.app.way.R;
import beta.app.way.entities.Friend;
import beta.app.way.utils.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public class FriendViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imgAvatar)
    ImageView ivAvatar;
    @BindView(R.id.mainHolder)
    LinearLayout mainHolder;
    @BindView(R.id.tvName)
    TextView tvName;

    private Activity context;

    public FriendViewHolder(@NonNull Activity activity, @NonNull View itemView) {
        super(itemView);
        context = activity;
        ButterKnife.bind(this, itemView);
    }

    public void setData(@NonNull Friend friend) {
        init(friend);
    }

    private void init(@NonNull Friend friend) {
        ImageLoader.loadImage(context, friend.getAvatar(), ivAvatar);
        tvName.setText(friend.getName().split(" ")[0]);
        mainHolder.bringToFront();
    }
}
