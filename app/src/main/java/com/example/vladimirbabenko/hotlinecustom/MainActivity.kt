package com.example.vladimirbabenko.hotlinecustom

import android.content.Intent
import android.os.Bundle
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //TODO( insert from DataManager)
    //var isUserLoggedIn: Boolean = DataManager

    if (true) startActivity(Intent(this, LoginActivityJ::class.java))
    //    else
    //      startActivity(this,)
  }
}
