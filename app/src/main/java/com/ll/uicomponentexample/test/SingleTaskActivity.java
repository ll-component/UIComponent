package com.ll.uicomponentexample.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ll.uicomponentexample.R;

public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = SingleTaskActivity.class.getName() + "--------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.div_standard:
                startActivity(new Intent(this, StandardActivity.class));
                break;
            case R.id.div_single_top:
                startActivity(new Intent(this, SingleTopActivity.class));
                break;
            case R.id.div_single_task:
                startActivity(new Intent(this, SingleTaskActivity.class));
                break;
            case R.id.div_single_instance:
                startActivity(new Intent(this, SingleInstanceActivity.class));
                break;
        }
    }
}