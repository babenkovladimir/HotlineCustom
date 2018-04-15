package com.example.vladimirbabenko.hotlinecustom.data.cashe

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BaseCashe<in T, out B>(context: Context, PREFS_KEY: String, jsonKey: String) {

  val prefs: SharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
  val jsonKey = jsonKey

  fun saveList(list: List<T>): Unit {
    val objectsList = Gson().toJson(list)
    //Log.d("TAG", "/n/p list entities : " + objectsList)
    prefs.edit().putString(jsonKey, objectsList).apply()
  }

  fun getList(): List<B> {
    val jsonObject = prefs.getString(jsonKey, "NoNe string")
    val type = object : TypeToken<ArrayList<B>>() {}.type
    val res = Gson().fromJson<List<B>>(jsonObject, type).toList()

    return res
  }
}