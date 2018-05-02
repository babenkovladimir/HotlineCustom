package com.example.vladimirbabenko.hotlinecustom.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus

abstract class BaseActivity() : AppCompatActivity() {

  lateinit var dataManager: DataManager
  val bus:GlobalBus = GlobalBus.instance

  init {
    Log.d("TAG", "BASE ACTIVITI INIT BLOCK")
  }

  open val TAG: String = "TAG"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base)
    dataManager = DataManager.create
    bus.register(this)
    Log.d("TAG", "BaseActivivti onCreate dataManager = " + dataManager)
  }

  fun Context.extendedToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  fun Context.logd(message: String) {
    Log.d("TAG", message)
  }

  override fun onDestroy() {
    bus.unregister(this)
    super.onDestroy()
  }
}
