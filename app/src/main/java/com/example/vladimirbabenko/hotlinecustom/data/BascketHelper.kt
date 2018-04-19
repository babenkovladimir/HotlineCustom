package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BascketHelper(val context: Context, val BASCKET_PREFERENCEC_KEY: String,
  val json_key: String) {

  val prefs = context.getSharedPreferences(BASCKET_PREFERENCEC_KEY, Context.MODE_PRIVATE)
  val gson = Gson()
  val type = object :TypeToken<MutableSet<Int>>() {}.type

  fun put(id:Int) {
    val set:MutableSet<Int> = get().toMutableSet()
    set.add(id)
    prefs.edit().putString(json_key, gson.toJson(set)).apply()
  }

  fun get(): MutableSet<Int> {
    val set: MutableSet<Int>

    val jsonString = prefs.getString(json_key, "")
    Log.d("TAGBASCKET", jsonString)
    if(jsonString.equals("")) {return emptySet<Int>().toMutableSet()}
    set = gson.fromJson(jsonString, type)
    return set
  }
}