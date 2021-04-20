package com.ll.uicomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * PackageName:    com.ll.uicomponent
 * ClassName:      RotateTextView
 * Author:         dev-gxy
 * CreateDate:     2021/4/20 15:42
 * SearchKeywords:   可旋转角度的文本，斜体显示文本
 */
public class RotateTextView extends AppCompatTextView {

    private int degrees = 0;// 旋转角度0~360

    public RotateTextView(Context context) {
        super(context);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public RotateTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setGravity(Gravity.CENTER);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RotateTextView);
        degrees = ta.getInteger(R.styleable.RotateTextView_rt_degrees, 0);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        canvas.rotate(degrees, this.getWidth() / 2f, this.getHeight() / 2f);
        super.onDraw(canvas);
        canvas.restore();
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
        invalidate();
    }
}