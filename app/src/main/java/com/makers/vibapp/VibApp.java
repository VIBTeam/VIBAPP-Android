package com.makers.vibapp;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Eliseo on 20/02/2016.
 */
public class VibApp extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        context = getApplicationContext();
    }

    public static boolean isDebug(){
        return BuildConfig.DEBUG;
    }

    public static Context getContext(){
        return context;
    }

}
