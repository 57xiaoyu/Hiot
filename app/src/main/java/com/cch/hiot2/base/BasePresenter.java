package com.cch.hiot2.base;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BasePresenter<T extends BaseView> {

    public BasePresenter() {
    }

    private T mView;

    public void setView(T mvpView) {
        this.mView = mvpView;
    }

    public T getView() {
        return mView;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void detachView() {
        if (mView != null) {
            this.mView = null;
        }
    }

    //公共部分
    public <R> void toSubscribe(Observable<R> o, Subscriber<R> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}

















