package com.ll.uicomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponent
 * ClassName:      DivergenceShadowButton
 * Author:         dev-gxy
 * CreateDate:     2021/5/15 10:56
 * Description:    带发散阴影样式的按钮
 */
public class DivergenceShadowButton extends View implements LifecycleObserver {

    private int mWidth;// view宽高
    private RectF mRectF;// 按钮本身部分
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private int mSolidStartColor = Color.parseColor("#FF6064");// 边框渐变色start
    private int mSolidEndColor = Color.parseColor("#FF3682");// 边框渐变色end
    private int[] mColors;
    private float[] mPositions;
    private Paint mTextPaint;
    private String mText;
    private int mCenterY;
    private Paint.FontMetrics mFontMetrics;
    private int mShadowTopMargin = 0;
    private int mButtonHeight = 0;
    private int mShadowHeight = 0;
    private float mRadius = 0;
    private Bitmap mShadowBitmap;
    private Rect mBitmapSrcRect, mBitmapDesRect;

    public DivergenceShadowButton(@NonNull Context context) {
        super(context);
        init(null);
    }

    public DivergenceShadowButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DivergenceShadowButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    private void init(AttributeSet attrs) {
        mColors = new int[]{mSolidStartColor, mSolidEndColor};
        mPositions = new float[]{0.2f, 1f};// 颜色相对位置

        mRectF = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true); //设置抗颜色抖动（让渐变更平滑）

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DivergenceShadowButton);

            int divTextSize = ta.getInteger(R.styleable.DivergenceShadowButton_divTextSize, 14);
            int divTextColor = ta.getColor(R.styleable.DivergenceShadowButton_divTextColor, Color.WHITE);
            mText = ta.getString(R.styleable.DivergenceShadowButton_divText);
            mTextPaint.setTextSize(dp2Px(divTextSize));
            mTextPaint.setColor(divTextColor);

            mButtonHeight = (int) ta.getDimension(R.styleable.DivergenceShadowButton_divButtonHeight, dp2Px(35));
            mShadowHeight = (int) ta.getDimension(R.styleable.DivergenceShadowButton_divShadowImgHeight, dp2Px(55));
            mShadowTopMargin = (int) ta.getDimension(R.styleable.DivergenceShadowButton_divShadowImgMarginTop, 0);
            mRadius = ta.getDimension(R.styleable.DivergenceShadowButton_divRadius, dp2Px(20));
            boolean divTextBold = ta.getBoolean(R.styleable.DivergenceShadowButton_divTextBold, false);
            mTextPaint.setTypeface(divTextBold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);

            BitmapDrawable shadowImgDrawable = (BitmapDrawable) ta.getDrawable(R.styleable.DivergenceShadowButton_divShadowImgSrc);
            if (shadowImgDrawable == null) {
                mShadowBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_shadow_btn);
            } else {
                mShadowBitmap = shadowImgDrawable.getBitmap();
            }
            mBitmapSrcRect = new Rect(0, 0, mShadowBitmap.getWidth(), mShadowBitmap.getHeight());
            mBitmapDesRect = new Rect();
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        mRectF.set(0, 0, mWidth, mButtonHeight);
        mLinearGradient = new LinearGradient(0, 0, mRectF.right, 0,
                mColors, mPositions, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);

        mBitmapDesRect.set(0, mShadowTopMargin, mWidth, mShadowTopMargin + mShadowBitmap.getHeight());

        int newHeight = mShadowHeight == 0 ? mButtonHeight : mShadowTopMargin + mShadowHeight;
        setMeasuredDimension(mWidth, newHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
        canvas.drawBitmap(mShadowBitmap, mBitmapSrcRect, mBitmapDesRect, null);

        mFontMetrics = mTextPaint.getFontMetrics();// 要在设置字体大小或者样式之后，不然获取到下面的top、bottom都不准
        float top = mFontMetrics.top;
        float bottom = mFontMetrics.bottom;
        mCenterY = (int) (mRectF.centerY() - top / 2 - bottom / 2);
        canvas.drawText(mText, mRectF.centerX(), mCenterY, mTextPaint);
    }

    /**
     * 绑定声明周期
     *
     * @param owner LifecycleOwner
     *              activity.bindLifecycleObserver(this)
     */
    public void bindLifecycleObserver(LifecycleOwner owner) {
        if (owner != null) {
            owner.getLifecycle().addObserver(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (mShadowBitmap != null && !mShadowBitmap.isRecycled()) {
            mShadowBitmap.recycle();
        }
    }

    private int dp2Px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }
}
