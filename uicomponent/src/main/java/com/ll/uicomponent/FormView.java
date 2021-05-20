package com.ll.uicomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
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
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;
import androidx.databinding.adapters.TextViewBindingAdapter;

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
    private boolean form_edit_input_enable;
    private EditText editText;
    private TextView tv_name, tv_unit;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return !form_edit_input_enable;
        }
        return super.onInterceptTouchEvent(ev);
    }



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

    public EditText getEditText() {
        return editText;
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

    public void setFormInputEnable(boolean form_edit_input_enable) {
        this.form_edit_input_enable = form_edit_input_enable;
        editText.setFocusableInTouchMode(form_edit_input_enable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mParentHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    private int dp2Px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }





    private void init(Context context, AttributeSet attrs) {
        removeAllViews();
        View view = LayoutInflater.from(context).inflate(R.layout.library_layout_form, this, true);
        tv_name = view.findViewById(R.id.tv_name);
        tv_unit = view.findViewById(R.id.tv_unit);
        editText = view.findViewById(R.id.edit);
        ImageView img_end_icon = view.findViewById(R.id.img_end_icon);
        View bottom_line = view.findViewById(R.id.bottom_line);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FormView);
            String form_name = ta.getString(R.styleable.FormView_form_name);
            int form_name_text_size = (int) ta.getInteger(R.styleable.FormView_form_name_text_size, 14);
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
            tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, form_name_text_size);
            tv_name.setTextColor(form_name_text_color);
            tv_name.setTypeface(Typeface.create(Typeface.DEFAULT, form_name_text_style));

            String form_unit = ta.getString(R.styleable.FormView_form_unit);
            int form_unit_text_size = (int) ta.getInteger(R.styleable.FormView_form_unit_text_size, 14);
            int form_unit_text_style = ta.getInteger(R.styleable.FormView_form_unit_text_style, Typeface.BOLD);
            int form_unit_text_color = ta.getColor(R.styleable.FormView_form_unit_text_color, Color.parseColor("#333333"));
            if (form_unit != null) {
                tv_unit.setText(form_unit);
            }
            tv_unit.setTextSize(TypedValue.COMPLEX_UNIT_SP, form_unit_text_size);
            tv_unit.setTextColor(form_unit_text_color);
            int form_unit_marginEnd = (int) ta.getDimension(R.styleable.FormView_form_unit_marginEnd, 0);
            ConstraintLayout.LayoutParams unitLayoutParams = (LayoutParams) tv_unit.getLayoutParams();
            unitLayoutParams.setMarginEnd(dp2Px(form_unit_marginEnd));

            tv_unit.setVisibility(form_unit == null ? GONE : VISIBLE);
            tv_unit.setTypeface(Typeface.create(Typeface.DEFAULT, form_unit_text_style));
            Drawable form_end_icon = ta.getDrawable(R.styleable.FormView_form_end_icon);
            if (form_end_icon == null) {
                img_end_icon.setVisibility(GONE);
                unitLayoutParams.endToEnd = 0;
            } else {
                img_end_icon.setVisibility(VISIBLE);
                img_end_icon.setImageDrawable(form_end_icon);
                unitLayoutParams.startToEnd = R.id.img_end_icon;
            }


            int form_bottom_line_color = ta.getColor(R.styleable.FormView_form_bottom_line_color, Color.parseColor("#E0E0E0"));
            boolean form_bottom_line_gone = ta.getBoolean(R.styleable.FormView_form_bottom_line_gone, false);
            // 分割线相对于输入框是否使用startToStart属性，默认true
            boolean form_bottom_line_startToStart_edit = ta.getBoolean(R.styleable.FormView_form_bottom_line_startToStart_edit, true);
            ConstraintLayout.LayoutParams layoutParams = (LayoutParams) bottom_line.getLayoutParams();
            if (form_bottom_line_startToStart_edit) {
                layoutParams.startToStart = R.id.edit;
                layoutParams.endToEnd = R.id.edit;
            } else {
                layoutParams.startToStart = 0;
                layoutParams.endToEnd = 0;
            }
            layoutParams.width = 0;
            bottom_line.setLayoutParams(layoutParams);
            bottom_line.setBackgroundColor(form_bottom_line_color);
            bottom_line.setVisibility(form_bottom_line_gone ? GONE : VISIBLE);

            String form_edit_hint = ta.getString(R.styleable.FormView_form_edit_hint);
            String form_edit_text = ta.getString(R.styleable.FormView_form_edit_text);
            int form_edit_hint_color = ta.getColor(R.styleable.FormView_form_edit_hint_color, Color.parseColor("#CCCCCC"));
            int form_edit_text_size = (int) ta.getInteger(R.styleable.FormView_form_edit_text_size, 14);
            int form_edit_text_color = ta.getColor(R.styleable.FormView_form_edit_text_color, Color.parseColor("#333333"));
            int form_edit_max_length = ta.getInteger(R.styleable.FormView_form_edit_max_length, 1000);
            int form_edit_input_type = ta.getInteger(R.styleable.FormView_form_edit_input_type, InputType.TYPE_CLASS_TEXT);
            form_edit_input_enable = ta.getBoolean(R.styleable.FormView_form_edit_input_enable, true);
            int form_edit_text_style = ta.getInteger(R.styleable.FormView_form_edit_text_style, Typeface.NORMAL);
            int form_edit_marginStart = (int) ta.getDimension(R.styleable.FormView_form_edit_marginStart, dp2Px(13));
            int form_edit_paddingTop = (int) ta.getDimension(R.styleable.FormView_form_edit_paddingTop, 14);
            setFormInputEnable(form_edit_input_enable);
            int form_edit_gravity = ta.getInteger(R.styleable.FormView_form_edit_gravity, 7);
            ConstraintLayout.LayoutParams layoutParams1 = (LayoutParams) editText.getLayoutParams();
            layoutParams1.setMarginStart(form_edit_marginStart);
            switch (form_edit_gravity) {
                case 0:
                    editText.setGravity(Gravity.CENTER_VERTICAL);
                    break;
                case 1: {
                    editText.setGravity(Gravity.TOP);
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
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, form_edit_text_size);
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

    public void setFormName(String name) {
        tv_name.setText(name);
    }

    public void setFormUnit(String unit) {
        tv_unit.setText(unit);
        tv_unit.setVisibility(VISIBLE);
    }

    @BindingAdapter("form_edit_text")
    public static void setValue(FormView formView, String value) {
        if (formView != null) {
            String edTextString = formView.editText.getText() == null ? "" : formView.editText.getText().toString();
            value = value == null ? "" : value;
            if (edTextString.equalsIgnoreCase(value)) {
                return;
            }
            formView.editText.setText(value);
        }
    }

    @InverseBindingAdapter(attribute = "form_edit_text", event = "valueAttrChanged")
    public static String getValue(FormView formView) {
        return formView.editText.getText().toString();
    }

    @BindingAdapter(
            value = {"android:beforeTextChanged",
                    "android:onTextChanged",
                    "android:afterTextChanged",
                    "valueAttrChanged"},
            requireAll = false)
    public static void setTextWatcher(
            FormView view,
            final TextViewBindingAdapter.BeforeTextChanged before,
            final TextViewBindingAdapter.OnTextChanged on,
            final TextViewBindingAdapter.AfterTextChanged after,
            final InverseBindingListener valueAttrChanged) {
        TextWatcher newWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (before != null) {
                    before.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (on != null) {
                    on.onTextChanged(s, start, before, count);
                }
                if (valueAttrChanged != null) {
                    valueAttrChanged.onChange();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (after != null) {
                    after.afterTextChanged(s);
                }
            }
        };
        TextWatcher oldWatcher = ListenerUtil.trackListener(view, newWatcher, R.id.textWatcher);
        if (oldWatcher != null) {
            view.editText.removeTextChangedListener(oldWatcher);
        }
        view.editText.addTextChangedListener(newWatcher);
    }
}
