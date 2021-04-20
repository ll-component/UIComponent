package com.ll.uicomponent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * ProjectName:    Live
 * Package:        com.lib.base.view
 * ClassName:      ShapeCornersTextView
 * Author:         dev-gxy
 * CreateDate:     2021/3/12 11:20
 * Description:
 */

public class ShapeTextView extends androidx.appcompat.widget.AppCompatTextView {

    // 用于绘制渐变色边框 其他情况直接使用GradientDrawable更方便，属性也更全
    private RectF mRectF;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private int mShapeStrokeStartColor = -1;// 边框渐变色start
    private int mShapeStrokeEndColor = -1;// 边框渐变色end
    private int mColorOrientation;// 颜色渐变方向
    private float mShapeRadius;// shape半径
    private float mStrokeWidth;// 边框线宽


    public ShapeTextView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ShapeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShapeTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private int dp2Px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mShapeStrokeStartColor == -1 || mShapeStrokeEndColor == -1) {
            return;
        }
        int[] colors = {mShapeStrokeStartColor, mShapeStrokeEndColor};
        float[] position = {0.4f, 1f};// 颜色相对位置
        if (mStrokeWidth == 0) mStrokeWidth = dp2Px(1);
        // 绘制带边框的矩形（其他形状同理），矩形的边界是边框的中心，而不是边框的边界，所以在绘制这些带边框的形状时，需要剔除边框宽度，不然会出现边框展示不全
        // 包括下面的LinearGradient也要剔除边框宽度
        mRectF = new RectF(0.0f + mStrokeWidth, 0.0f + mStrokeWidth, getMeasuredWidth() - mStrokeWidth, getMeasuredHeight() - mStrokeWidth);
        if (mColorOrientation == 0) { // LEFT_RIGHT
            mLinearGradient = new LinearGradient(0 + mStrokeWidth, 0 + mStrokeWidth, 0 - mStrokeWidth, mRectF.bottom - mStrokeWidth,
                    colors, position, Shader.TileMode.CLAMP);
        } else if (mColorOrientation == 6) {//  TOP_BOTTOM
            mLinearGradient = new LinearGradient(0 + mStrokeWidth, 0 + mStrokeWidth, mRectF.right - mStrokeWidth, 0 - mStrokeWidth,
                    colors, position, Shader.TileMode.CLAMP);
        } else if (mColorOrientation == 2) {//  RIGHT_LEFT
            mLinearGradient = new LinearGradient(mRectF.right + mStrokeWidth, 0 + mStrokeWidth, 0 - mStrokeWidth, 0 - mStrokeWidth,
                    colors, position, Shader.TileMode.CLAMP);
        } else if (mColorOrientation == 4) {//  BOTTOM_TOP
            mLinearGradient = new LinearGradient(0 + mStrokeWidth, mRectF.bottom + mStrokeWidth, 0 - mStrokeWidth, 0 - mStrokeWidth,
                    colors, position, Shader.TileMode.CLAMP);
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true); //设置抗颜色抖动（让渐变更平滑）
        mPaint.setShader(mLinearGradient);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShapeStrokeStartColor == -1 || mShapeStrokeEndColor == -1) {
            return;
        }
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawRoundRect(mRectF, mShapeRadius, mShapeRadius, mPaint);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
            mShapeRadius = ta.getDimension(R.styleable.ShapeTextView_shapeRadius, 0);
            float topLeftRadius = ta.getDimension(R.styleable.ShapeTextView_shapeTopLeftRadius, 0);
            float topRightRadius = ta.getDimension(R.styleable.ShapeTextView_shapeTopRightRadius, 0);
            float bottomLeftRadius = ta.getDimension(R.styleable.ShapeTextView_shapeBottomLeftRadius, 0);
            float bottomRightRadius = ta.getDimension(R.styleable.ShapeTextView_shapeBottomRightRadius, 0);
            int solidColor = ta.getColor(R.styleable.ShapeTextView_shapeSolidColor, Color.TRANSPARENT);
            int strokeColor = ta.getColor(R.styleable.ShapeTextView_shapeStrokeColor, Color.TRANSPARENT);
            mStrokeWidth = ta.getDimension(R.styleable.ShapeTextView_shapeStrokeWidth, 0);
            int shape = ta.getInt(R.styleable.ShapeTextView_shape, GradientDrawable.RECTANGLE);
            int startColor = ta.getColor(R.styleable.ShapeTextView_shapeStartColor, Color.TRANSPARENT);
            int endColor = ta.getColor(R.styleable.ShapeTextView_shapeEndColor, Color.TRANSPARENT);
            mColorOrientation = ta.getInt(R.styleable.ShapeTextView_shapeColorOrientation, 6);
            mShapeStrokeStartColor = ta.getColor(R.styleable.ShapeTextView_shapeStrokeStartColor, -1);
            mShapeStrokeEndColor = ta.getColor(R.styleable.ShapeTextView_shapeStrokeEndColor, -1);
            GradientDrawable.Orientation orientation;
            switch (mColorOrientation) {
                case 0:
                    orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                    break;
//                case 1:
//                    orientation = GradientDrawable.Orientation.TR_BL;
//                    break;
                case 2:
                    orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                    break;
//                case 3:
//                    orientation = GradientDrawable.Orientation.BR_TL;
//                    break;
                case 4:
                    orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                    break;
//                case 5:
//                    orientation = GradientDrawable.Orientation.BL_TR;
//                    break;
//                case 7:
//                    orientation = GradientDrawable.Orientation.TL_BR;
//                    break;
                default:
                    orientation = GradientDrawable.Orientation.LEFT_RIGHT;
            }

            if (mShapeRadius > 0 || topLeftRadius > 0 || topRightRadius > 0 || bottomLeftRadius > 0 || bottomRightRadius > 0) {
                GradientDrawable drawable = new GradientDrawable();
                if (startColor != 0 || endColor != 0) {
                    drawable.setOrientation(orientation);
                    drawable.setColors(new int[]{startColor, endColor});
                } else {
                    drawable.setColor(solidColor);// 填充色
                }
                drawable.setShape(shape);
                if (mStrokeWidth > 0) {
                    drawable.setStroke((int) mStrokeWidth, strokeColor);// 边框
                }
                if (topLeftRadius > 0 || topRightRadius > 0 || bottomLeftRadius > 0 || bottomRightRadius > 0) {
                    float[] radius = new float[]{
                            topLeftRadius, topLeftRadius,
                            topRightRadius, topRightRadius,
                            bottomRightRadius, bottomRightRadius,
                            bottomLeftRadius, bottomLeftRadius};
                    drawable.setCornerRadii(radius);
                } else {
                    drawable.setCornerRadius(mShapeRadius);
                }
                setBackground(drawable);
            }

            ta.recycle();
        }
    }
}
