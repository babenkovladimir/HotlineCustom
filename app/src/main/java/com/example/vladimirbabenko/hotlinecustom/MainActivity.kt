package com.example.vladimirbabenko.hotlinecustom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    var isUserLoggedIn: Boolean = dataManager.prefs.userLoggedIn
    Log.d("TAG", "Mainactivity")

    if (isUserLoggedIn) {
      startActivity(Intent(this, MainScreenActivityJ::class.java))
    } else {
      startActivity(Intent(this, LoginActivity::class.java))
    }
  }
}
