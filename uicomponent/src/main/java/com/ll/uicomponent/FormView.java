package com.ll.uicomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * ProjectName:    Live
 * Package:        com.lib.base.view
 * ClassName:      FormView
 * Author:         dev-gxy
 * CreateDate:     2021/4/14 14:25
 * Description: 表单自定义view
 */
public class FormView extends ConstraintLayout {

    private int mParentHeight;

    public FormView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public FormView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FormView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 获取输入的文字内容
     */
    public String getInputStr() {
        if (editText == null) {
            return null;
        }
        return editText.getText().toString().trim();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mParentHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    private int dp2Px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    public EditText getEditText() {
        return editText;
    }

    private EditText editText;

    private void init(Context context, AttributeSet attrs) {
        removeAllViews();
        View view = LayoutInflater.from(context).inflate(R.layout.library_layout_form, this, true);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_unit = view.findViewById(R.id.tv_unit);
        editText = view.findViewById(R.id.edit);
        ImageView img_end_icon = view.findViewById(R.id.img_end_icon);
        View bottom_line = view.findViewById(R.id.bottom_line);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FormView);

            String form_name = ta.getString(R.styleable.FormView_form_name);
            int form_name_text_size = (int) ta.getDimension(R.styleable.FormView_form_name_text_size, 14);
            int form_name_marginTop = (int) ta.getDimension(R.styleable.FormView_form_name_marginTop, 14);
            int form_name_text_color = ta.getColor(R.styleable.FormView_form_name_text_color, Color.parseColor("#333333"));
            int form_name_text_style = ta.getInteger(R.styleable.FormView_form_name_text_style, Typeface.BOLD);
            boolean form_name_centerVertical = ta.getBoolean(R.styleable.FormView_form_name_centerVertical, true);
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topToTop = 0;
            if (form_name_centerVertical) {
                params.bottomToBottom = 0;
            } else {
                params.topMargin = form_name_marginTop;
            }
            tv_name.setLayoutParams(params);
            tv_name.setText(form_name);
            tv_name.setTextSize(form_name_text_size);
            tv_name.setTextColor(form_name_text_color);
            tv_name.setTypeface(Typeface.create(Typeface.DEFAULT, form_name_text_style));

            String form_unit = ta.getString(R.styleable.FormView_form_unit);
            int form_unit_text_size = (int) ta.getDimension(R.styleable.FormView_form_unit_text_size, 14);
            int form_unit_text_color = ta.getColor(R.styleable.FormView_form_unit_text_color, Color.parseColor("#333333"));
            if (!TextUtils.isEmpty(form_unit)) {
                tv_unit.setText(form_unit);
                tv_unit.setTextSize(form_unit_text_size);
                tv_unit.setTextColor(form_unit_text_color);
            }
            tv_unit.setVisibility(TextUtils.isEmpty(form_unit) ? GONE : VISIBLE);

            Drawable form_end_icon = ta.getDrawable(R.styleable.FormView_form_end_icon);
            if (form_end_icon == null) {
                img_end_icon.setVisibility(GONE);
            } else {
                img_end_icon.setVisibility(VISIBLE);
                img_end_icon.setImageDrawable(form_end_icon);
            }

            int form_bottom_line_margin_start = (int) ta.getDimension(R.styleable.FormView_form_bottom_line_margin_start, -1);
            int form_bottom_line_color = ta.getColor(R.styleable.FormView_form_unit_text_color, Color.parseColor("#E0E0E0"));
            boolean form_bottom_line_gone = ta.getBoolean(R.styleable.FormView_form_bottom_line_gone, false);

            ConstraintLayout.LayoutParams layoutParams = (LayoutParams) bottom_line.getLayoutParams();
            if (form_bottom_line_margin_start > 0) {
                layoutParams.startToStart = 0;
                layoutParams.setMarginStart(form_bottom_line_margin_start);
            } else {
                layoutParams.startToStart = R.id.edit;
                layoutParams.endToEnd = R.id.edit;
                layoutParams.width = 0;
            }
            bottom_line.setLayoutParams(layoutParams);
            bottom_line.setBackgroundColor(form_bottom_line_color);
            bottom_line.setVisibility(form_bottom_line_gone ? GONE : VISIBLE);

            String form_edit_hint = ta.getString(R.styleable.FormView_form_edit_hint);
            String form_edit_text = ta.getString(R.styleable.FormView_form_edit_text);
            int form_edit_hint_color = ta.getColor(R.styleable.FormView_form_edit_hint_color, Color.parseColor("#CCCCCC"));
            int form_edit_text_size = (int) ta.getDimension(R.styleable.FormView_form_edit_text_size, 14);
            int form_edit_text_color = ta.getColor(R.styleable.FormView_form_edit_text_color, Color.parseColor("#333333"));
            int form_edit_max_length = ta.getInteger(R.styleable.FormView_form_edit_max_length, 1000);
            int form_edit_input_type = ta.getInteger(R.styleable.FormView_form_edit_input_type, InputType.TYPE_CLASS_TEXT);
            boolean form_edit_input_enable = ta.getBoolean(R.styleable.FormView_form_edit_input_enable, true);
            int form_edit_text_style = ta.getInteger(R.styleable.FormView_form_edit_text_style, Typeface.NORMAL);
            int form_edit_paddingTop = (int) ta.getDimension(R.styleable.FormView_form_edit_paddingTop, 14);
            editText.setFocusableInTouchMode(form_edit_input_enable);
            this.form_edit_input_enable = form_edit_input_enable;
            int form_edit_gravity = ta.getInteger(R.styleable.FormView_form_edit_gravity, 7);
            switch (form_edit_gravity) {
                case 0:
                    editText.setGravity(Gravity.CENTER_VERTICAL);
                    break;
                case 1: {
                    editText.setGravity(Gravity.TOP);
                    ConstraintLayout.LayoutParams layoutParams1 = (LayoutParams) editText.getLayoutParams();
                    layoutParams1.topMargin = form_edit_paddingTop;
                    break;
                }
                case 2:
                    editText.setGravity(Gravity.BOTTOM);
                    break;
                case 3:
                    editText.setGravity(Gravity.START);
                    break;
                case 4:
                    editText.setGravity(Gravity.END);
                    break;
                case 5: {
                    editText.setGravity(Gravity.TOP | Gravity.START);
                    ConstraintLayout.LayoutParams layoutParams1 = (LayoutParams) editText.getLayoutParams();
                    layoutParams1.topMargin = form_edit_paddingTop;
                    break;
                }
                case 6:
                    editText.setGravity(Gravity.CENTER);
                    break;
                case 7:
                    editText.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                    break;
                case 8:
                    editText.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                    break;
            }
            editText.setHint(form_edit_hint);
            editText.setHintTextColor(form_edit_hint_color);
            editText.setTextSize(form_edit_text_size);
            editText.setTextColor(form_edit_text_color);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(form_edit_max_length)});
            if (form_edit_input_type == InputType.TYPE_TEXT_FLAG_MULTI_LINE) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            } else {
                editText.setInputType(form_edit_input_type);
            }

            editText.setTypeface(Typeface.create(Typeface.DEFAULT, form_edit_text_style));
            editText.setText(form_edit_text);
            int form_background_color = ta.getColor(R.styleable.FormView_form_background_color, -1);
            if (form_background_color != -1) {
                setBackgroundColor(form_background_color);
            }
            ta.recycle();
        }
    }

    private boolean form_edit_input_enable;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return !form_edit_input_enable;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
