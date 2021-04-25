package com.ll.uicomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.widget.AppCompatEditText;

public class ClearEditTextView extends AppCompatEditText implements OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable;
    private Drawable mStartDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFocus;

    public ClearEditTextView(Context context) {
        super(context);
        init(null);
    }

    public ClearEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ClearEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    /**
     * Returns drawables for the left, top, right, and bottom borders.
     *
     * @attr ref android.R.styleable#TextView_drawableLeft 0
     * @attr ref android.R.styleable#TextView_drawableTop 1
     * @attr ref android.R.styleable#TextView_drawableRight 2
     * @attr ref android.R.styleable#TextView_drawableBottom 3
     */
    private void init(AttributeSet attrs) {
        setBackground(null);
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ClearEditTextView);
            mClearDrawable = ta.getDrawable(R.styleable.ClearEditTextView_searchClearIcon);
            mStartDrawable = ta.getDrawable(R.styleable.ClearEditTextView_searchStartIcon);
            if (mClearDrawable == null) {
                mClearDrawable = getResources().getDrawable(R.mipmap.ic_default_delete, null);
            }
            if (mStartDrawable == null) {
                mStartDrawable = getResources().getDrawable(R.mipmap.ic_default_search, null);
            }
            int searchClearIconWidth = (int) ta.getDimension(R.styleable.ClearEditTextView_searchClearIconWidth, dp2Px(14));
            int searchClearIconHeight = (int) ta.getDimension(R.styleable.ClearEditTextView_searchClearIconHeight, dp2Px(14));
            int searchStartIconWidth = (int) ta.getDimension(R.styleable.ClearEditTextView_searchStartIconWidth, dp2Px(14));
            int searchStartIconHeight = (int) ta.getDimension(R.styleable.ClearEditTextView_searchStartIconHeight, dp2Px(14));
            mClearDrawable.setBounds(0, 0, searchClearIconWidth, searchClearIconHeight);
            mStartDrawable.setBounds(0, 0, searchStartIconWidth, searchStartIconHeight);
            // shape半径
            float mShapeRadius = ta.getDimension(R.styleable.ClearEditTextView_searchBgShapeRadius, 0);
            // 边框线宽
            float mStrokeWidth = ta.getDimension(R.styleable.ClearEditTextView_searchBgShapeStrokeWidth, 0);
            int strokeColor = ta.getColor(R.styleable.ClearEditTextView_searchBgShapeStrokeColor, Color.TRANSPARENT);
            int solidColor = ta.getColor(R.styleable.ClearEditTextView_searchBgShapeSolidColor, Color.TRANSPARENT);
            GradientDrawable drawable = new GradientDrawable();
            if (mStrokeWidth > 0) {
                drawable.setStroke((int) mStrokeWidth, strokeColor);// 边框
            }
            drawable.setCornerRadius(mShapeRadius);
            drawable.setColor(solidColor);
            setBackground(drawable);
            setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
        }

        setClearIconVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    private int dp2Px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 记住按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(mStartDrawable, getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFocus) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation(int counts) {
        this.setAnimation(shakeAnimation(counts));
    }

    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动次数
     */
    private Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        return translateAnimation;
    }


}
