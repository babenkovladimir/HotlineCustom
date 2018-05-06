package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BascketHelper(val context: Context, val BASCKET_PREFERENCEC_KEY: String,
  val json_key: String) {

  val prefs = context.getSharedPreferences(BASCKET_PREFERENCEC_KEY, Context.MODE_PRIVATE)
  val gson = Gson()
  val type = object : TypeToken<MutableSet<BascketItem>>() {}.type

  fun put(item: BascketItem) {
    val set: MutableSet<BascketItem> = get().toMutableSet()
    set.add(item)
    prefs.edit().putString(json_key, gson.toJson(set)).apply()
  }

  fun putAll(items: MutableSet<BascketItem>){
    clearBascket()
    prefs.edit().putString(json_key, gson.toJson(items)).apply()
  }

  fun get(): MutableSet<BascketItem> {
    val set: MutableSet<BascketItem>
    val jsonString = prefs.getString(json_key, "")

    if (jsonString.equals("")) {
      return emptySet<BascketItem>().toMutableSet()
    }

    set = gson.fromJson(jsonString, type)
    return set
  }

  fun clearBascket(){
    prefs.edit().putString(json_key, "").apply()
  }

  fun removeFromBaskcet(item: BascketItem?) {
    val set: MutableSet<BascketItem> = get().toMutableSet()

    var newSet: MutableSet<BascketItem> = emptySet<BascketItem>().toMutableSet()

    for(i  in 0..set.size-1)
      if (!set.elementAt(i).id!!.equals(item?.id)) {newSet.add(set.elementAt(i))}

    prefs.edit().putString(json_key, gson.toJson(newSet)).apply()
  }
}