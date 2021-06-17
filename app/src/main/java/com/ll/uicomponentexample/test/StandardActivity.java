package com.ll.uicomponentexample.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.ll.uicomponentexample.R;

public class StandardActivity extends Activity implements View.OnClickListener {

    private static final String TAG = StandardActivity.class.getName() + "--------";
    private AlertDialog sysDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        Log.i(TAG, "onCreate: ");

//        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//        intent.setData(Uri.parse("package:" + getPackageName()));
//        startActivityForResult(intent, 100);


        sysDialog = new AlertDialog.Builder(this)
                .setTitle("系统弹窗")
                .setMessage("弹窗")
                .create();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sysDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

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
        switch (v.getId()) {
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
            case R.id.div_sys_dialog:
                sysDialog.show();
                finish();
                break;
        }
    }

}