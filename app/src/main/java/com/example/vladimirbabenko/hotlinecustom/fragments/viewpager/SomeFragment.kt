package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.data.DataManager.Companion

class SomeFragment(): Fragment() {

  lateinit var dataManager:DataManager

  companion object {
    val title = "Some..."

    fun newInstance(bundle: Bundle?):SomeFragment {
      val fragment = SomeFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataManager = DataManager.create(context!!)
  }

}