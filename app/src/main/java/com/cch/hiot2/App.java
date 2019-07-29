package com.cch.hiot2;

import android.app.Application;

import com.cch.hiot2.injection.component.ApplicationComponent;
import com.cch.hiot2.injection.component.DaggerApplicationComponent;
import com.cch.hiot2.injection.module.ApplicationModule;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class App extends Application {

    private ApplicationComponent component;
    private static ApplicationComponent s_component;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();

        //初始化Zxing
        ZXingLibrary.initDisplayOpinion(this);

    }

    private void initInjector() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        s_component=component;
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }public static ApplicationComponent getSComponent() {
        return s_component;
    }
}
