package com.sunruncn.unitdemo;

import android.content.Context;
import android.support.multidex.MultiDex;

import ico.ico.ico.BaseApplication;


/**
 * Created by ICO on 2016/6/13 0013.
 */
public class MyApplication extends BaseApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
