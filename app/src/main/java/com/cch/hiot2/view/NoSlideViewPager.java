package com.cch.hiot2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoSlideViewPager extends ViewPager {
    public NoSlideViewPager(@NonNull Context context) {
        super(context);
    }

    public NoSlideViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean isSlidable = true;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isSlidable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isSlidable && super.onInterceptTouchEvent(ev);
    }

    public void setSlidable(boolean slidable) {
        isSlidable = slidable;
    }
}
