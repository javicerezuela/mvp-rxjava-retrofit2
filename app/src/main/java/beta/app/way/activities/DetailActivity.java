package beta.app.way.activities;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import beta.app.way.R;
import beta.app.way.core.BaseActivity;
import beta.app.way.entities.Friend;
import beta.app.way.utils.ImageLoader;
import butterknife.BindView;

/**
 * Created by panjiyudasetya on 12/22/16.
 */

public class DetailActivity extends BaseActivity {
    private static final String EXTRA_DATA = "EXTRA_DATA";

    @BindView(R.id.imgPreview)
    ImageView mIvPreview;
    @BindView(R.id.tvName)
    TextView mTvName;

    @Override
    public int withLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void init() {
        super.init();
        Friend extra = new Gson().fromJson(getIntent().getStringExtra(EXTRA_DATA), Friend.class);
        ViewCompat.setTransitionName(mIvPreview, EXTRA_DATA);

        ImageLoader.loadImage(this, extra.getAvatar(), mIvPreview);
        mTvName.setText(extra.getName().split(" ")[0]);
    }

    public static void launch(BaseActivity activity, View transitionView, Friend friend) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_DATA);
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_DATA, new Gson().toJson(friend));
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
