package com.ll.uicomponentexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ll.uicomponentexample.example.ChameleonViewExampleActivity;
import com.ll.uicomponentexample.example.ClearEditTextViewExampleActivity;
import com.ll.uicomponentexample.example.EmptyViewExampleActivity;
import com.ll.uicomponentexample.example.FormViewExampleActivity;
import com.ll.uicomponentexample.example.RotateTextViewExampleActivity;
import com.ll.uicomponentexample.example.ShapeTextViewExampleActivity;
import com.ll.uicomponentexample.example.SuctionBottomLayoutExampleActivity;
import com.ll.uicomponentexample.example.TouchEventTestActivity;
import com.ll.uicomponentexample.test.StandardActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            case R.id.rotateTextView:// RotateTextView
                startActivity(new Intent(this, RotateTextViewExampleActivity.class));
                break;
            case R.id.clearEditTextView:// ClearEditTextView
                startActivity(new Intent(this, ClearEditTextViewExampleActivity.class));
                break;
            case R.id.chameleonView:// chameleonView
                startActivity(new Intent(this, ChameleonViewExampleActivity.class));
                break;
            case R.id.suctionBottomLayout:// suctionBottomLayout
                startActivity(new Intent(this, SuctionBottomLayoutExampleActivity.class));
                break;
            case R.id.touchEventTest:
                startActivity(new Intent(this, TouchEventTestActivity.class));
                break;
            case R.id.div_task:
                startActivity(new Intent(this, StandardActivity.class));
                break;
        }
    }
}