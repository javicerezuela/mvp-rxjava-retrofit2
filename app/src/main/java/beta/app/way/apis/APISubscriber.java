package beta.app.way.apis;

import android.support.annotation.NonNull;
import com.google.gson.JsonParseException;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * This is derived from {@link Subscriber}. In my case, I want to make generated error become
 * readable by human.
 *
 * Created by panjiyudasetya on 12/19/16.
 */

public class APISubscriber<T> extends Subscriber<T> {
    private APIErrorHandler mErrorHandler;

    public enum ErrorType {
        API, JSON, UNKNOWN
    }

    public APISubscriber(@NonNull APIErrorHandler errorHandler) {
        mErrorHandler = errorHandler;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {
        String errorMessage = e.getMessage();
        // obtain http exception
        if (e instanceof HttpException) {
            HttpException httpError = (HttpException) e;
            errorMessage = mErrorHandler.getMessage(httpError.code(), httpError.message());
            onError(errorMessage, ErrorType.API);
        } else if (e instanceof JsonParseException) {
            onError(errorMessage, ErrorType.JSON);
        } else {
            onError(errorMessage, ErrorType.UNKNOWN);
        }
    }

    public void onError(@NonNull String message, @NonNull ErrorType errorType) {}
}
