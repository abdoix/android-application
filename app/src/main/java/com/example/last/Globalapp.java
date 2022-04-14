package com.example.last;

import android.app.Application;
import android.content.Context;

public class Globalapp extends Application {
    private static Context appcontext;

    public static Context getAppcontext() {
        return appcontext;
    }

    public static void setAppcontext(Context appcontext) {
        Globalapp.appcontext = appcontext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appcontext = getApplicationContext();
    }
}
