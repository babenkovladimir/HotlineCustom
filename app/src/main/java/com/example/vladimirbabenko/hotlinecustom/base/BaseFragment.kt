package com.example.vladimirbabenko.hotlinecustom.base

import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

open class BaseFragment: Fragment() {

  lateinit var dataManager:DataManager

  init {
  }

  protected fun Context.logd(message:String) = Log.d("",message)

}