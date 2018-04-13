package com.example.vladimirbabenko.hotlinecustom.data.j;

import android.content.Context;
import android.content.SharedPreferences;

/*
*
* Apply - он асинхронный
* */

class BasePreferencesHelperJ {

    private SharedPreferences mPreferences;

    BasePreferencesHelperJ(Context context) {
        mPreferences = context.getSharedPreferences("PreferencesHelper",
            Context.MODE_PRIVATE);
    }

    private SharedPreferences getPreferences() {
        return mPreferences;
    }

    void setInt(String key, int value) {
        getPreferences().edit().putInt(key, value).apply();
    }

    int getInt(String key) {
        return getPreferences().getInt(key, -1);
    }

    void setString(String key, String value) {
        getPreferences().edit().putString(key, value).apply();
    }

    String getString(String key) {
        return getPreferences().getString(key, "");
    }

    void setBoolean(String key, Boolean param) {
        getPreferences().edit().putBoolean(key, param).apply();
    }

    boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }
}
