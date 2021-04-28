package com.ll.uicomponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * ProjectName:    android
 * Package:        com.cqsbym.common.view
 * ClassName:      SuctionBottomButton
 * Author:         dev-gxy
 * CreateDate:     2021/4/28 10:59
 * Description:  吸底并带顶部阴影条的layout
 * 绝大部分layout吸底时都采用了白色背景，所以先将颜色固定，后期再写进AttributeSet中
 * 为了完全满足设计师提出的阴影效果，这里没有采用setShadowLayer或setMaskFilter方法来实现，这个方式实现的最终效果设计师说不是很好,不自然
 * 所以这里采用了设计师给出的阴影图片加上绘制白色背景来实现吸底并带顶部阴影条的layout
 * 绘制时需要剔除阴影图片的高度
 * 顶部部分---iconView---
 * 底部部分---RectF(需从top为iconView高度值的位置开始绘制白色背景)---
 */
public class SuctionBottomLayout extends LinearLayout {

    private Paint mPaint;
    private RectF mRectF;

    public SuctionBottomLayout(Context context) {
        super(context);
        init(null);
    }

    public SuctionBottomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SuctionBottomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private int dp2Px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    private void init(AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mRectF = new RectF();

        setBackground(null);// 防止在xml中手误设置了背景，这里将背景置空
        removeAllViews();
        View view = new View(getContext());
        view.setBackgroundResource(R.mipmap.ic_line_shadow);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2Px(5)));
        addView(view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        mRectF.set(0.0f, dp2Px(5), widthMeasure, heightMeasure);
    }

    /**
     * ViewGroup需要自定义绘制时，需要在dispatchDraw方法中进行，下面的onDraw方法只有在设置过ViewGroup
     * 的背景后才会调用，不然只会默认调用dispatchDraw，
     * 设置过背景  onDraw -- dispatchDraw
     * 没有设置过背景 dispatchDraw
     *
     * @param canvas Canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawRoundRect(mRectF, 0.0f, 0.0f, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
