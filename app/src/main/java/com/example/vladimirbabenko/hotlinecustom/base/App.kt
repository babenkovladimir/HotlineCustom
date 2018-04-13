package com.example.vladimirbabenko.hotlinecustom.base

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.data.DataManager.Companion
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper

class App:Application() {

  init {
    instance = this
  }

  companion object {
    private var instance: App? = null

    fun applicationContext() : Context {
      return instance!!.applicationContext
    }
  }

  override fun onCreate() {
    super.onCreate()

    val context: Context = App.applicationContext()

    //appPrefs = PreferencesHelper(this)
    //val dataManager:DataManager = DataManager.create()
    //appPrefs = dataManager.prefs
  }



}