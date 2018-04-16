package com.example.vladimirbabenko.hotlinecustom.data.cashe

import android.content.Context
import android.content.SharedPreferences
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CasheCarPart(private val context: Context, private val PREFS_KEY: String, private val jsonKey: String) {

  var prefs: SharedPreferences
  var gson: Gson
  val type = object : TypeToken<List<CarPart>>() {}.type

  init {
    prefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
    gson = Gson()
  }

  fun saveList(list: List<CarPart>) {
    val json = gson.toJson(list, type)
    prefs.edit().putString(jsonKey, json).apply()
  }

  fun getList(): List<CarPart> {
    val jsonToParce = prefs.getString(jsonKey, "NoNe string")
    return gson.fromJson(jsonToParce, type)
  }
}