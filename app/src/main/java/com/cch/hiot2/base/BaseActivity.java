package com.cch.hiot2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cch.hiot2.App;
import com.cch.hiot2.injection.component.ActivityComponent;
import com.cch.hiot2.injection.component.ApplicationComponent;
import com.cch.hiot2.injection.component.DaggerActivityComponent;
import com.cch.hiot2.injection.module.ActivityModule;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter> extends AppCompatActivity implements BaseView{

    private P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        mvpPresenter = createPresenter();
        if(mvpPresenter!=null) {
            mvpPresenter.setView((V) this);
        }
    }

    protected abstract void injectDependencies();

    protected abstract P createPresenter();

    private ActivityComponent mActivityComponent;
    public ActivityComponent getActivityComponent(){
        if(mActivityComponent==null){
            mActivityComponent= DaggerActivityComponent.builder()
                    .activityModule(getActivityModule())
                    .applicationComponent(getApplicationComponent())
                    .build();
        }

        return mActivityComponent;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((App)getApplication()).component();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mvpPresenter!=null){
            mvpPresenter.detachView();
        }
    }
}
