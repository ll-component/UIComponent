package com.ll.uicomponentexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.ll.uicomponentexample.example.bean.MyAidlBean;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponentexample
 * ClassName:      MyAidlService
 * Author:         dev-gxy
 * CreateDate:     2021/5/13 13:06
 * Description:
 */
public class MyAidlService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public MyAidlBean getData() throws RemoteException {
            MyAidlBean myAidlBean = new MyAidlBean();
            myAidlBean.setName("aidl");
            myAidlBean.setName("0");
            return myAidlBean;
        }
    };
}
