package com.ll.uicomponentexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ll.uicomponent.ChameleonView;
import com.ll.uicomponentexample.example.ClearEditTextViewExampleActivity;
import com.ll.uicomponentexample.example.EmptyViewExampleActivity;
import com.ll.uicomponentexample.example.FormViewExampleActivity;
import com.ll.uicomponentexample.example.RotateTextViewExampleActivity;
import com.ll.uicomponentexample.example.ShapeTextViewExampleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChameleonView chameleon = findViewById(R.id.chameleon);
        int[] colors = {
                Color.parseColor("#A833FF"),
                Color.parseColor("#FFD320"),
                Color.parseColor("#FF4B73")};
        float[] childValues = {10.6f, 32.21f, 20.56f};
        chameleon.setColors(colors, childValues, 100.00f);
        chameleon.bindLifecycleObserver(this);
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
            case R.id.rotateTextView:// RotateTextView
                startActivity(new Intent(this, RotateTextViewExampleActivity.class));
                break;
            case R.id.clearEditTextView:// ClearEditTextView
                startActivity(new Intent(this, ClearEditTextViewExampleActivity.class));
                break;
        }
    }
}