package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants

open class BasePreferencesHelper (context: Context) {

  private val _sharedPreferences: SharedPreferences

  init {
    _sharedPreferences = context.getSharedPreferences(AppConstants.APP_PREFERENCES.key, Context.MODE_PRIVATE)
  }

  fun setBoolean(key: String, value: Boolean) = _sharedPreferences.edit().putBoolean(key, value).apply()

  fun getBoolean(key: String): Boolean = _sharedPreferences.getBoolean(key, false)

  fun setInt(key: String, value: Int) = _sharedPreferences.edit().putInt(key, value).apply()

  fun getInt(key: String) = _sharedPreferences.getInt(key, -1)

  fun setString(key: String, value: String): Unit {_sharedPreferences.edit().putString(key, value).apply() }

  fun getString(key: String): String { return _sharedPreferences.getString(key, "") }

  fun setLong(key:String, value:Long): Unit{_sharedPreferences.edit().putLong(key, value).apply() }

  fun getLong(key:String):Long{ return _sharedPreferences.getLong(key,-1) }

}