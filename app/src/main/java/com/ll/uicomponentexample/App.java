package com.ll.uicomponentexample;

import android.app.Application;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponentexample
 * ClassName:      App
 * Author:         dev-gxy
 * CreateDate:     2021/5/25 14:07
 * Description:
 */
public class App extends Application {

    private static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
