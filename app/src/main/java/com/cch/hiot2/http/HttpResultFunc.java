package com.cch.hiot2.http;

import android.util.Log;
import rx.functions.Func1;

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    private static final String TAG = "HttpResultFunc";

    @Override
    public T call(HttpResult<T> httpResult) {
        Log.i(TAG, "status:" + httpResult.getStatus());
        Log.i(TAG, "msg:" + httpResult.getMsg());
        Log.i(TAG, "data:" + httpResult.getData());
        return httpResult.getData();
    }
}
