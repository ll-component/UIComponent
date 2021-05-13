package com.ll.uicomponentexample.example;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ll.uicomponentexample.R;

public class TouchEventTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_test);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TouchEventTestActivity.this, "点击了textview", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("touch--------", "onTouchEvent: Activity" + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("touch--------", "dispatchTouchEvent: Activity" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }


}