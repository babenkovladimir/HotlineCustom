package com.example.vladimirbabenko.hotlinecustom.data.cashe

import android.content.Context
import android.content.SharedPreferences
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CasheVideoCard(private val context: Context, private val PREFS_KEY: String,
  private val jsonKey: String) {

  var prefs: SharedPreferences
  var gson: Gson
  val type = object : TypeToken<List<VideoCard>>() {}.type

  init {
    prefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
    gson = Gson()
  }

  fun saveList(list: List<VideoCard>) {
    val json = gson.toJson(list, type)
    prefs.edit().putString(jsonKey, json).apply()
  }

  fun getList(): List<VideoCard> {
    val jsonToParce = prefs.getString(jsonKey, "NoNe string")
    return gson.fromJson(jsonToParce, type)
  }
}