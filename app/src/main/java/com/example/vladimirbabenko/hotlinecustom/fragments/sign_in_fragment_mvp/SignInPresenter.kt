package com.example.vladimirbabenko.hotlinecustom.fragments.sign_in_fragment_mvp

import android.app.PendingIntent.getActivity
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.base.App
import com.example.vladimirbabenko.hotlinecustom.base.App.Companion
import com.example.vladimirbabenko.hotlinecustom.base.mvp.BasePresenter
import com.example.vladimirbabenko.hotlinecustom.entity.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

class SignInPresenter() : BasePresenter<SignInFragment>() {

  val mAuth = FirebaseAuth.getInstance()

  fun signIn(email: String?, password: String?) {

    var emailError: String? = if (isValidEmail(email.toString())) null else "email error"
    var passwordError: String? = if (isValidPassword(password.toString())) null else "passwordError"

    if (emailError == null && passwordError == null) {
      signInWithEmailAndPassword(email as String, password as String)
    } else getView()?.setErrors(emailError, passwordError)
  }

  // Private helpers

  private fun signInWithEmailAndPassword(email: String, password: String) {
    mAuth.signInWithEmailAndPassword(email, password)
      .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
        override fun onComplete(task: Task<AuthResult>) {
          if (task.isSuccessful) { // updateUI
            val user = mAuth.currentUser
            if (saveUserInfoToPreferences(user!!)) {
              getView()?.activity?.finishAffinity()
              getView()?.startMainScreen()
            }
            Log.d("TAGSIGNIN", "succsesfull")
          } else Toast.makeText(getView()!!.context, task.exception?.localizedMessage,
            Toast.LENGTH_LONG).show()
        }
      }).addOnFailureListener {
        object : OnFailureListener {
          override fun onFailure(exception: Exception) {
            Log.d("TAGSIGNIN", "Noton Failru - " + exception.cause)
          }
        }
      }
  }

  private fun saveUserInfoToPreferences(fireUser: FirebaseUser?): Boolean {
    with(fireUser!!) {
      val id = uid
      val email = email
      val displayedName = displayName
      val givenName: String? = null
      val familyName: String? = null
      val photoUrl = fireUser.photoUrl.toString()
      val user = User(id, email!!, displayedName, familyName, givenName, photoUrl)


      mDataManager.prefs.userEmail = email
      mDataManager.prefs.userInJson = user
      mDataManager.prefs.userLoggedIn = true
      mDataManager.prefs.userByFireBase = true
    }

    return true
  }

  private fun isValidEmail(email: CharSequence): Boolean {
    return if (TextUtils.isEmpty(email)) {
      false
    } else {
      android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
  }

  private fun isValidPassword(password: CharSequence): Boolean {
    return if (password.length < 6 && password.length > 0) false else true
  }
}