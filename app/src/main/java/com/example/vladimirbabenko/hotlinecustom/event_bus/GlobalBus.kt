package com.example.vladimirbabenko.hotlinecustom.event_bus

import android.util.Log
import com.squareup.otto.Bus

class GlobalBus private constructor():Bus() {

  init{ Log.d("TAG", "Global bus init") }

  private object Holder {
    val INSTANCE = GlobalBus()
  }

  companion object {
    val instance: GlobalBus by lazy {Holder.INSTANCE }
  }
}