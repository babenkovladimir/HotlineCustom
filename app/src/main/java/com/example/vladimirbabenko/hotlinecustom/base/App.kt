package com.example.vladimirbabenko.hotlinecustom.base

import android.app.Application
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper

class App:Application() {

  companion object {
    var appPrefs:PreferencesHelper? = null
  }

  override fun onCreate() {
    super.onCreate()
    appPrefs = PreferencesHelper(this)
  }
}