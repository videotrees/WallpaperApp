package com.wallpaper.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Navdeep on 8/23/2017.
 */

public class AppPref {
    Context context;
    SharedPreferences sharedpreferences;

    public static final String APP_PREF = "wallpaper_preference";
    public static final String APP_KEY_COUNTER = "app_key_counter";

    public AppPref(Context context){
        this.context = context;
        sharedpreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
    }

    public void saveKeyCounter(int counter){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(APP_KEY_COUNTER, counter);
        editor.commit();
    }

    public int getKeyCounter(){
        return sharedpreferences.getInt(APP_KEY_COUNTER,0);
    }

    public String getSearchKey(){

        return "";
    }


}
