package beta.app.way.apis;

import java.io.IOException;
import beta.app.way.BuildConfig;
import rx.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * API Configurations class
 *
 * Created by panjiyudasetya on 12/21/16.
 */

public class APIConfigurations {
    private static String BASE_URL;
    protected static API APIService;

    public APIConfigurations() {
        init();
    }

    public void init() {
        BASE_URL = getUrl();
        if (APIService == null) {
            // Initialize Http Logging
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel( BuildConfig.DEBUG ? Level.BODY : Level.NONE );

            // Initialize http client
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(logging)
                    .build();

            // Initialize retrofit builder
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(httpClient)
                    .build();

            // Create API Service
            APIService = retrofit.create(API.class);
        }
    }

    private static String getUrl() {
        if (BuildConfig.FLAVOR.contentEquals("release"))
            return API.PRODUCTION_URL;

        return API.APIARY_URL;
    }
}
