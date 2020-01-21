package com.e.quizapphw;

import android.app.Application;

import com.e.quizapphw.data.PreferenceHelper;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.init(this);
    }

}
