package com.ll.uicomponentexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponentexample.view
 * ClassName:      TouchEventTestTextView
 * Author:         dev-gxy
 * CreateDate:     2021/5/11 18:02
 * Description:
 */
public class TouchEventTestTextView extends AppCompatTextView {
    public TouchEventTestTextView(Context context) {
        super(context);
    }

    public TouchEventTestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventTestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("touch--------", "onTouchEvent: TextView" + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("touch--------", "dispatchTouchEvent: TextView" + super.dispatchTouchEvent(event));
        return super.dispatchTouchEvent(event);
    }
}
