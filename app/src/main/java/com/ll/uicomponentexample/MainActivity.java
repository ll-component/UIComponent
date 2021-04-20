package com.ll.uicomponentexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ll.uicomponent.RotateTextView;
import com.ll.uicomponentexample.example.EmptyViewExampleActivity;
import com.ll.uicomponentexample.example.FormViewExampleActivity;
import com.ll.uicomponentexample.example.RotateTextViewExampleActivity;
import com.ll.uicomponentexample.example.ShapeTextViewExampleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.emptyView:// 空视图
                startActivity(new Intent(this, EmptyViewExampleActivity.class));
                break;
            case R.id.formView:// 表单项
                startActivity(new Intent(this, FormViewExampleActivity.class));
                break;
            case R.id.shapeTextView:// shape文本
                startActivity(new Intent(this, ShapeTextViewExampleActivity.class));
                break;
            case R.id.rotateTextView:// rotateTextView
                startActivity(new Intent(this, RotateTextViewExampleActivity.class));
                break;
        }
    }
}