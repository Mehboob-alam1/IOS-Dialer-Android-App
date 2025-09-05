package com.callos16.callscreen.colorphone;

import android.app.Application;
import android.content.Context;
import com.callos16.callscreen.colorphone.admin.MyApplication;

import java.util.Collections;

public class MyAppClass extends Application {

    public static Context myContext;
    @Override
    public void onCreate() {
        super.onCreate();
        myContext = this;
        
        // Initialize admin MyApplication for admin mode functionality
        MyApplication.getInstance();
    }
}
