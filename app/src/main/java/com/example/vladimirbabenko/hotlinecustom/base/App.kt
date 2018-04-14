package com.example.vladimirbabenko.hotlinecustom.base

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import com.example.vladimirbabenko.hotlinecustom.utils.BattaryChargingReceiver

class App : Application() {

  lateinit var battaryChargingReceiver: BattaryChargingReceiver

  init {
    instance = this
  }

  companion object {
    private var instance: App? = null

    fun applicationContext(): Context {
      return instance!!.applicationContext
    }
  }

  override fun onCreate() {
    super.onCreate()
    val context: Context = App.applicationContext()


    registerChargingReceiver()
  }




  private fun registerChargingReceiver(): Unit { // O - Api 26
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val intentFilter = IntentFilter()
      battaryChargingReceiver = BattaryChargingReceiver()
      intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
      intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
      intentFilter.addAction("vladimir.babenko.hotline.custom.chek.charge.state");
      registerReceiver(battaryChargingReceiver, intentFilter)
    }
  }
}