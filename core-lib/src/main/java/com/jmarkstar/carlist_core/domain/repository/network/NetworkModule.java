package com.jmarkstar.carlist_core.domain.repository.network;

import android.content.Context;

import com.jmarkstar.carlist_core.util.Constants;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by jmarkstar on 22/08/2017.
 */
@Module
public final class NetworkModule {

    private String mBaseUrl;

    public NetworkModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    ApiTestService provideApiTestService(Retrofit retrofit){
        return retrofit.create(ApiTestService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient,
                             ThreadPoolExecutor threadPoolExecutor) {
        return new Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(mBaseUrl)
            .callbackExecutor(threadPoolExecutor)
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(Constants.HTTP_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(Constants.HTTP_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(Constants.HTTP_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        //httpClientBuilder.cache(cache);
        return httpClientBuilder.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("OkHttp").d(message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
