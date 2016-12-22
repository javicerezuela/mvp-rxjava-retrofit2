package beta.app.way.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by panjiyudasetya on 12/19/16.
 */

public class LogHelper {
    private static final String LOG_TAG = "LOG_HELPER";

    public static void printLog(@NonNull String message) {
        Log.d(LOG_TAG, "======= " + message);
    }

    public static void showToast(@NonNull Context context, @NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
