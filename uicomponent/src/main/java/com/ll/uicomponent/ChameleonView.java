package com.ll.uicomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponent
 * ClassName:      ChameleonView
 * Author:         dev-gxy
 * CreateDate:     2021/4/27 11:00
 * Description: 可显示不同色块的条状bar，适用于展示存储空间信息
 */
public class ChameleonView extends View implements LifecycleObserver {

    private int mWidth, mHeight;// view宽高
    private ArrayList<Paint> mPaints;// 绘制色块
    private ArrayList<RectF> mRectFS;// 绘制色块
    private float mMaxValue;// 最大值
    private float[] mChildValues;// 每个色块的值
    private RectF mClipRectF;// 裁剪背景
    private Path mClipPath;// 裁剪背景
    private int clShapeRadius;// 裁剪的圆角 view背景的圆角

    public ChameleonView(Context context) {
        super(context);
        init(null);
    }

    public ChameleonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ChameleonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mClipRectF = new RectF();
        mClipPath = new Path();
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ChameleonView);
            clShapeRadius = (int) ta.getDimension(R.styleable.ChameleonView_clShapeRadius, 0);
            int clShapeSolidColor = ta.getColor(R.styleable.ChameleonView_clShapeSolidColor, Color.parseColor("#F2F2F2"));
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(clShapeRadius);
            drawable.setColor(clShapeSolidColor);
            setBackground(drawable);
            ta.recycle();
        }
    }

    /**
     * 绑定声明周期
     *
     * @param owner LifecycleOwner
     */
    public void bindLifecycleObserver(LifecycleOwner owner) {
        if (owner != null) {
            owner.getLifecycle().addObserver(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        mPaints.clear();
        mRectFS.clear();
    }

    /**
     * @param colors      色块集合
     * @param childValues 色块对应值的集合
     */
    public void setColors(int[] colors, float[] childValues, float maxValue) {
        mPaints = new ArrayList<>();
        mRectFS = new ArrayList<>();
        this.mChildValues = childValues;
        this.mMaxValue = maxValue;
        if (colors == null) {
            throw new IllegalStateException("colors cannot be null");
        }
        if (childValues == null) {
            throw new IllegalStateException("childValues cannot be null");
        }
        if (colors.length != childValues.length) {
            throw new IllegalStateException("The colors length must be equal to the childValues length");
        }
        float allValues = 0;
        for (int i = 0; i < colors.length; i++) {
            allValues += childValues[i];
            if (allValues > maxValue) {
                throw new IllegalStateException("The total value of the color block cannot be greater than the maximum value");
            }
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(colors[i]);
            mPaints.add(paint);

            RectF rectF = new RectF();
            mRectFS.add(rectF);
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mClipRectF.set(0.0f, 0.0f, mWidth, mHeight);
    }

    /**
     * 0,drawWidth
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // clipPath
        mClipPath.addRoundRect(mClipRectF, clShapeRadius, clShapeRadius, Path.Direction.CW);
        canvas.clipPath(mClipPath);
        super.onDraw(canvas);
        // draw color
        float startLeft = 0;
        for (int i = 0; i < mPaints.size(); i++) {
            float pr = mChildValues[i] / mMaxValue;
            float drawWidth = pr * mWidth;
            mRectFS.get(i).set(startLeft, 0.0f, startLeft + drawWidth, mHeight);
            startLeft += drawWidth;
            canvas.drawRect(mRectFS.get(i), mPaints.get(i));
        }
    }
}
