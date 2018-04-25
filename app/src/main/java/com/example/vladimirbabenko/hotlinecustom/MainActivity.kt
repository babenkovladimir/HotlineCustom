package com.example.vladimirbabenko.hotlinecustom

import android.content.Intent
import android.os.Bundle
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val isUserLoggedIn: Boolean = dataManager.prefs.userLoggedIn

    if (isUserLoggedIn) {
      finishAffinity()
      startActivity(Intent(this, MainScreenActivity::class.java))
    } else {
      finishAffinity()
      startActivity(Intent(this, LoginActivity::class.java))
    }

  }
}
