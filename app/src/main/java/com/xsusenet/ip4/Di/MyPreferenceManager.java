package com.xsusenet.ip4.Di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferenceManager {
    SharedPreferences.Editor editor;
    Context context;
    private final SharedPreferences mSharedPreferences;
   static MyPreferenceManager instance;

    public MyPreferenceManager(Application application) {
        instance=this;

        this.context=application.getApplicationContext();
        String prefsFile = context.getPackageName();
        mSharedPreferences= context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }
    public static MyPreferenceManager getInstance()
    {
        return instance;
    }

    public void savePref(String key, Object value) {
        delete(key);

        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-primitive preference");
        }

        editor.apply();
    }
    public void delete(String key) {
        if (mSharedPreferences.contains(key)) {
            editor.remove(key).commit();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key) {
        return (T) mSharedPreferences.getAll().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key, T defValue) {
        T returnValue = (T) mSharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }
}