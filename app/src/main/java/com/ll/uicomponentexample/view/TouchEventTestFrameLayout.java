package com.ll.uicomponentexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponentexample.view
 * ClassName:      TouchEventTestFrameLayout
 * Author:         dev-gxy
 * CreateDate:     2021/5/11 18:01
 * Description:
 */
public class TouchEventTestFrameLayout extends FrameLayout {
    public TouchEventTestFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TouchEventTestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventTestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("touch--------", "dispatchTouchEvent: FrameLayout" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("touch--------", "onInterceptTouchEvent: FrameLayout" + super.onInterceptTouchEvent(ev));
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("touch--------", "onTouchEvent: FrameLayout" + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
