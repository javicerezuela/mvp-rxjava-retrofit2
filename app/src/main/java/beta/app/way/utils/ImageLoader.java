package beta.app.way.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public abstract class ImageLoader {
    public static void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
    public static void loadImage(@NonNull Activity context, @NonNull String url, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
    public static void loadImage(@NonNull FragmentActivity context, @NonNull String url, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
    public static void loadImage(@NonNull Fragment context, @NonNull String url, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}
