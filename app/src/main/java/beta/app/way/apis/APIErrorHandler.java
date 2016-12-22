package beta.app.way.apis;

import android.content.Context;
import android.support.annotation.NonNull;

import beta.app.way.R;

/**
 * This class created to handle customizable request error message.
 * You can modify it with human readable string which defined on String resource.
 *
 * Created by panjiyudasetya on 12/22/16.
 */

public class APIErrorHandler {
    private Context mContext;

    public APIErrorHandler(@NonNull Context context) {
        mContext = context;
    }

    /**
     * Get readable error message based on http request code error.
     *
     * @param errorCode http error code
     * @param defaultMessage default message
     * @return Readable error message
     */
    public @NonNull String getMessage(int errorCode, @NonNull String defaultMessage) {
        switch (errorCode) {
            case 400 : return mContext.getResources().getString(R.string.network_error_400);
            case 401 : return mContext.getResources().getString(R.string.network_error_401);
            case 403 : return mContext.getResources().getString(R.string.network_error_403);
            case 404 : return mContext.getResources().getString(R.string.network_error_404);
            case 408 : return mContext.getResources().getString(R.string.network_error_408);
            default  : return defaultMessage;
        }
    }
}