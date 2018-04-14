package com.example.vladimirbabenko.hotlinecustom.base

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import com.example.vladimirbabenko.hotlinecustom.utils.BattaryChargingReceiver
import com.example.vladimirbabenko.hotlinecustom.utils.NetworkChangeReceiverJ

class App : Application() {

  lateinit var battaryChargingReceiver: BattaryChargingReceiver
  lateinit var networkChangeReceiver: NetworkChangeReceiverJ

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
    registerNetworkChangeReceiver()
  }

  private fun registerNetworkChangeReceiver() {
     networkChangeReceiver= NetworkChangeReceiverJ()
    val intentFilter=IntentFilter()
    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
    registerReceiver(networkChangeReceiver, intentFilter)
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