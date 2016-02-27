package com.makers.vibapp.util;

import android.util.Log;

import com.makers.vibapp.VibApp;

/**
 * Created by Eliseo on 20/02/2016.
 */
public class LogManager {

    public static final String TAG = "VIB APP";

    public static void v(String message){
        if(VibApp.isDebug())
            Log.v(TAG, message);
    }

    public static void d(String message){
        if(VibApp.isDebug())
            Log.d(TAG, message);
    }

    public static void i(String message){
        if(VibApp.isDebug())
            Log.i(TAG, message);
    }

    public static void w(String message){
        if(VibApp.isDebug())
            Log.e(TAG, message);
    }

    public static void e(String message){
        if(VibApp.isDebug())
            Log.e(TAG, message);
    }
}
