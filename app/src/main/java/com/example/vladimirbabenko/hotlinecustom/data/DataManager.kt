package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import kotlin.coroutines.experimental.coroutineContext

class DataManager( context: Context){

  private lateinit var preferencesHelper: PreferencesHelper

  init {
    val context:Context = context
    preferencesHelper = PreferencesHelper(context)
    Log.d("TAG", "DataManager initializing")
  }

  //private object DataManagerHolder() {
  //  init {
  //    println("DataMAnagerHolder initializing")
  //  }
  //  val INSTANCE = DataManager(context) }



companion object {

  //fun getInstance(context: Context)= DataManager by lazy { DataManagerHolder().INSTANCE }

  //val getInstanse:DataManager by lazy { DataManagerHolder.INSTANCE }
  }

  fun getPreferences():PreferencesHelper = preferencesHelper
}


