package com.abhi.inshortchallenge.utilities;

import android.app.Application;
import android.content.Context;

import com.abhi.inshortchallenge.model.DataManager;

/**
 *  Author: Chandra Prakash
 *  Description: custom application class to access application context.
 */

public class CustomApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DataManager.instance();
    }

    public static Context getmContext(){
        return mContext;
    }
}
