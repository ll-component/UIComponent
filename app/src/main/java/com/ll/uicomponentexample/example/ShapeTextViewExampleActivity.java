package com.ll.uicomponentexample.example;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ll.uicomponent.ShapeTextView;
import com.ll.uicomponentexample.R;

public class ShapeTextViewExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_text_view_example);

        ShapeTextView dyn_shape_tv = findViewById(R.id.dyn_shape_tv);
        dyn_shape_tv.setShapeSolidColor(ContextCompat.getColor(this,R.color.colorAccent));

        ShapeTextView dyn_shape_tv1 = findViewById(R.id.dyn_shape_tv1);
        dyn_shape_tv1.setShapeRadius(2);
    }
}