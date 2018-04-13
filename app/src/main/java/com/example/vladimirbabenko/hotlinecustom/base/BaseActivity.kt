package com.example.vladimirbabenko.hotlinecustom.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.data.DataManager.Companion
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper

open abstract class BaseActivity() : AppCompatActivity() {

  //  val dataManager: DataManager = DataManager(this)
  //  val prefs: PreferencesHelper = dataManager.prefs
  lateinit var dataManager: DataManager
  lateinit var prefs: PreferencesHelper

  init {
    Log.d("TAG", "BASE ACTIVITI INIT BLOCK")
    //dataManager = DataManager(this)
    //prefs = dataManager.prefs
  }

  open val TAG: String = "TAG"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base)
    dataManager = DataManager.create()
    Log.d("TAG","BAseActivivti onCreate dataManager = "+dataManager)
    prefs = dataManager.prefs
  }

  fun Context.extendedToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  fun Context.logd(message: String) {
    Log.d("TAG", message)
  }
}
