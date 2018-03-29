package com.example.vladimirbabenko.hotlinecustom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

open class BaseActivity() : AppCompatActivity() {

  open lateinit var dataManager: DataManager
  val TAG:String="TAG"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base)

    dataManager = DataManager();
  }
}
