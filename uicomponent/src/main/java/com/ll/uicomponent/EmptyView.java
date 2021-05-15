package com.ll.uicomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;


/**
 * PackageName:    com.lib.base.view
 * ClassName:      EmptyView
 * Author:         dev-gxy
 * CreateDate:     2021/2/2 16:29
 * SearchKeywords:
 * 使用需要声明：
 * 图片的高度 empty_icon_height
 * 图片icon  empty_icon
 * 文字 empty_title
 * 文字距离底部的距离（相对应图片的位置）  empty_title_margin_bottom
 */
@InverseBindingMethods({
        @InverseBindingMethod(
                type = EmptyView.class,
                attribute = "checkedValue",
                event = "checkedValueAttrChanged",
                method = "getCheckedValue")
})
public class EmptyView extends ConstraintLayout {

    public EmptyView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private int dp2Px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    private void init(Context context, AttributeSet attrs) {
        removeAllViews();
        View view = LayoutInflater.from(context).inflate(R.layout.library_layout_empty, this, true);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyView);
            ImageView icon = view.findViewById(R.id.icon);
            int empty_icon_height = (int) ta.getDimension(R.styleable.EmptyView_empty_icon_height, dp2Px(100));
            int empty_icon_width = (int) ta.getDimension(R.styleable.EmptyView_empty_icon_width, dp2Px(275));

            ConstraintLayout.LayoutParams iconParams = new ConstraintLayout.LayoutParams(
                    empty_icon_width, empty_icon_height);
            iconParams.startToStart = 0;
            iconParams.endToEnd = 0;

            int resourceId = ta.getResourceId(R.styleable.EmptyView_empty_icon, R.mipmap.base_ic_empty_money);
            icon.setBackgroundResource(resourceId);
            icon.setLayoutParams(iconParams);

            String title = ta.getString(R.styleable.EmptyView_empty_title);

            TextView textView = view.findViewById(R.id.title);
            textView.setText(title);
            boolean empty_title_topToBottom_icon = ta.getBoolean(R.styleable.EmptyView_empty_title_topToBottom_icon, false);
            int empty_title_color = (int) ta.getColor(R.styleable.EmptyView_empty_title_color, Color.parseColor("#999999"));
            textView.setTextColor(empty_title_color);

            int empty_title_size = (int) ta.getInteger(R.styleable.EmptyView_empty_title_size, 12);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, empty_title_size);

            int bottomToBottom_margin = (int) ta.getDimension(R.styleable.EmptyView_empty_title_bottomToBottom_margin, 10);
            int topToBottom_margin = (int) ta.getDimension(R.styleable.EmptyView_empty_title_topToBottom_margin, 10);

            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.startToStart = R.id.icon;
            params.endToEnd = R.id.icon;

            if (empty_title_topToBottom_icon) {
                params.topToBottom = R.id.icon;
                params.topMargin = topToBottom_margin;
            } else {
                params.bottomToBottom = R.id.icon;
                params.bottomMargin = bottomToBottom_margin;
            }
            textView.setLayoutParams(params);
            ta.recycle();
        }
    }

    public void setTitle(String title) {
        TextView textView = findViewById(R.id.title);
        textView.setText(title);
    }

    public void setEmptyIcon(int icon) {
        ImageView imageView = findViewById(R.id.icon);
        imageView.setBackgroundResource(icon);
    }

    @BindingAdapter(value = "empty_title", requireAll = false)
    public static void setTitle(EmptyView titleView, String title) {
        TextView textView = titleView.findViewById(R.id.title);
        textView.setText(title);
    }
}
