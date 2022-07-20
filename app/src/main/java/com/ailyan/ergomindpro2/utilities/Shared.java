package com.ailyan.ergomindpro2.utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Shared {
    private static final String TAG = Shared.class.getSimpleName();
    private static Shared instance;
    private final SharedPreferences sp;

    private Shared(Context context) {
        sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public static Shared getInstance(Context context) {
        if (instance == null)
            instance = new Shared(context.getApplicationContext());
        return instance;
    }

    public void put(Object value, String key) {
        SharedPreferences.Editor prefEditor = sp.edit();
        Gson gson = new Gson();
        prefEditor.putString(key, gson.toJson(value));
        prefEditor.apply();
    }

    public Object get(Class<?> target, String key) {
        Gson gson = new Gson();
        String data = sp.getString(key, "");
        return gson.fromJson(data, target);
    }
}
