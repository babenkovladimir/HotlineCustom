package com.example.vladimirbabenko.hotlinecustom

import android.content.Intent
import android.os.Bundle
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity() {

  lateinit var mAuth: FirebaseAuth
  lateinit var mAuthStateListner: FirebaseAuth.AuthStateListener

  init {
    mAuth = FirebaseAuth.getInstance()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (dataManager.prefs.userByFireBase) {
      mAuthStateListner = object : FirebaseAuth.AuthStateListener {
        override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
          val user = firebaseAuth.currentUser

          if (user == null) dataManager.prefs.clearUserPreferences()
        }
      }

      mAuth.addAuthStateListener(mAuthStateListner)
    }

    val isUserLoggedIn: Boolean = dataManager.prefs.userLoggedIn

    if (isUserLoggedIn) {
      finishAffinity()
      startActivity(Intent(this, MainScreenActivity::class.java))
    } else {
      finishAffinity()
      startActivity(Intent(this, LoginActivity::class.java))
    }
  }

  override fun onDestroy() {
//    if (mAuthStateListner != null) {
//      mAuth.removeAuthStateListener(mAuthStateListner as FirebaseAuth.AuthStateListener)
//    }
    super.onDestroy()
  }
}
