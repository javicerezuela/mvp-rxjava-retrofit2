package beta.app.way.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by panjiyudasetya on 12/16/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Subscription mSubscription = null;

    /** This should be implemented by the ancestor to determine what layout should be displayed */
    public abstract int withLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(withLayout());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}