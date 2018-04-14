package com.example.vladimirbabenko.hotlinecustom.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.vladimirbabenko.hotlinecustom.base.App

class InternetConnectionHelper{

  companion object {
    fun isConnection():Boolean {
      val cm:ConnectivityManager = (App.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
      val activeNetwork:NetworkInfo? = cm.activeNetworkInfo
      if (activeNetwork != null) {
        return activeNetwork.isConnectedOrConnecting
      } else return false
    }
  }
}