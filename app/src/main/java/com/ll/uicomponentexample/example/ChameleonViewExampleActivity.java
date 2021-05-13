package com.ll.uicomponentexample.example;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.ll.uicomponent.ChameleonView;
import com.ll.uicomponentexample.R;

public class ChameleonViewExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chameleon_view_example);

        ChameleonView chameleon = findViewById(R.id.chameleon);
        // 设置颜色
        int[] colors = {
                Color.parseColor("#A833FF"),
                Color.parseColor("#FFD320"),
                Color.parseColor("#FF4B73")};
        // 设置颜色对应的values值
        float[] childValues = {40.4f, 10.56f, 20.44f};
        chameleon.setColors(colors, childValues, 100.00f);
        // 绑定声明周期
        chameleon.bindLifecycleObserver(this);

        ChameleonView chameleon1 = findViewById(R.id.chameleon1);
        int[] colors1 = {
                Color.parseColor("#FF4B73")};
        float[] childValues1 = {60.4f};
        chameleon1.setColors(colors1, childValues1, 100.00f);
        chameleon1.bindLifecycleObserver(this);
    }
}