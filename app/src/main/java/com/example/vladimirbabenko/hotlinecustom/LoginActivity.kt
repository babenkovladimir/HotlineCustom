package com.example.vladimirbabenko.hotlinecustom

import android.os.Bundle
import android.view.View
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity

class LoginActivity : BaseActivity(), View.OnClickListener {

  //override val TAG:String by BaseActivity

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)


  }

  override fun onClick(view: View?) {
    TODO(
      "not implemented"
    ) //To change body of created functions use File | Settings | File Templates.

//    when (view!!.id){
//      R.id.btSignInGoogleButton-> signInWithGoogle()
////dataManager.getPreferences().
//
//    }
  }

  private fun signInWithGoogle(): Unit {

  }
}
