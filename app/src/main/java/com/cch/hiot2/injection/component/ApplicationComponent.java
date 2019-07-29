package com.cch.hiot2.injection.component;

import android.app.Application;
import android.content.Context;

import com.cch.hiot2.App;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.UserPreferencesHelper;
import com.cch.hiot2.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton//单例
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App application);
    Application application();//将Application开放给其他Component使用
    Context context();//将Context开放给其他Component使用
    HttpService httpService();//将HttpService开放给其他Component使用
    UserPreferencesHelper userPreferencesHelper();//将UserPreferencesHelper开放给其他Component使用
}















