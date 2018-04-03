package com.example.vladimirbabenko.hotlinecustom.base

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.data.DataManager.Companion
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper

class App:Application() {

  companion object {
    //var appPrefs:PreferencesHelper? = null
  }
  init {
    Log.d("TAGA pplication init", "Init block in App:Application()")
  }

  override fun onCreate() {
    super.onCreate()
    //appPrefs = PreferencesHelper(this)
    val dataManager:DataManager = DataManager.create(this)
    //appPrefs = dataManager.prefs
  }



}