package com.ll.uicomponentexample.example;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;

import com.ll.uicomponentexample.R;
import com.ll.uicomponentexample.databinding.ActivityFormViewExampleBinding;
import com.ll.uicomponentexample.example.bean.FormExampleBean;

import java.util.ArrayList;

public class FormViewExampleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFormViewExampleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_form_view_example);
        final FormExampleBean bean = new FormExampleBean();
        bean.setName("Form_Name");
        binding.setData(bean);
        binding.lastForm.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(FormViewExampleActivity.this, bean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.choose) {
            Toast.makeText(this, "点击了选择地区FormView", Toast.LENGTH_SHORT).show();
        }
    }
}