package com.ll.uicomponentexample.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ll.uicomponentexample.R;

public class FormViewExampleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view_example);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.choose) {
            Toast.makeText(this, "点击了选择地区FormView", Toast.LENGTH_SHORT).show();
        }
    }
}