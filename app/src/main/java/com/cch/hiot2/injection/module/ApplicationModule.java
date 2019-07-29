package com.cch.hiot2.injection.module;

import android.app.Application;
import android.content.Context;

import com.cch.hiot2.App;
import com.cch.hiot2.BuildConfig;
import com.cch.hiot2.http.HttpService;
import com.google.gson.Gson;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final App appliction;

    //Application不能new，通过构造函数传递
    public ApplicationModule(App appliction) {
        this.appliction = appliction;
    }

    @Provides
    Application provideApplication() {
        return appliction;
    }

    @Provides
    Context provideContext() {
        return appliction;
    }


    //提供HttpService
    @Provides
    @Singleton
    HttpService provideHttpService(Retrofit retrofit) {
        return retrofit.create(HttpService.class);
    }


    //提供OkHttpClient
    @Provides
    @Singleton//单例
    OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
                .writeTimeout(6, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    //提供Retrofit
    @Provides
    @Singleton//单例
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(HttpService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    //提供Gson
    @Provides
    @Singleton//单例
    Gson provideGson() {
        return new Gson();
    }
}























