package com.cch.hiot2.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by 昌华 on 2018/10/17.
 * <p>
 * 自带加载进度的观察者
 */

public abstract class ProgressDialogSubscriber<T> extends Subscriber<T> {

    private static final String TAG = "Progress";
    private final Context mContext;
    private ProgressDialog mDialog;

    public ProgressDialogSubscriber(Context context) {
        this.mContext = context;
    }
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d(TAG,"error:" + e.getMessage());
        }
        dismissProgressDialog();
    }
    private void dismissProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }
    private void showProgressDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(true);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    //取消订阅，取消请求
                    ProgressDialogSubscriber.this.unsubscribe();
                }
            });
        }
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }
}
