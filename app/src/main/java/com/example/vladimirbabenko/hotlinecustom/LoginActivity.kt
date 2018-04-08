package com.example.vladimirbabenko.hotlinecustom

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.entity.User
import com.example.vladimirbabenko.hotlinecustom.fragments.SignUpFragmentJ
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.btCheckForExitingUser
import kotlinx.android.synthetic.main.activity_login.btSignInGoogleButton
import kotlinx.android.synthetic.main.activity_login.btSignOutGoogle
import kotlinx.android.synthetic.main.activity_login.btSignUp
import kotlinx.android.synthetic.main.activity_login.btTerms

class LoginActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    setupUI()
  }

  private fun setupUI() {

    //btSignInGoogleButton.setOnClickListener(){v -> googleSignIn()}
    btSignInGoogleButton.setOnClickListener(object : View.OnClickListener {
      override fun onClick(p0: View?) {
        googleSignIn()
      }
    })
    btSignInGoogleButton.setSize(SignInButton.SIZE_STANDARD)

    btSignOutGoogle.setOnClickListener(){v->
      val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
      val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
      mGoogleSignInClient.signOut()}
    btCheckForExitingUser.setOnClickListener() {v -> checkForexitingSignedInUser() }
    btSignUp.setOnClickListener({showSignUpFragment()})

    btTerms.setOnClickListener({
      startActivity(Intent(applicationContext, TermsActivity::class.java))
    })
  }

  private fun checkForexitingSignedInUser(): Unit {
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

    if (account != null) {
      with(GoogleSignIn.getLastSignedInAccount(this)!!) {
        logd(email!!)
        logd(displayName!!)
        logd(photoUrl.toString()!!)
      }
    }else logd("User is S I G N E D   O U T")
  }

  fun onClick(view: View?) { //    when (view!!.id){
    //      R.id.btSignInGoogleButton-> signInWithGoogle()
    ////dataManager.getPreferences().
    //
    //    }
  }

  private fun googleSignIn(): Unit {
    val gso: GoogleSignInOptions =
      GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
    val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    val signInIntent = mGoogleSignInClient.signInIntent
    startActivityForResult(signInIntent, AppConstants.RC_GOOGLE_SIGN_IN.intKey!!)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int,
    data: Intent?) { //super.onActivityResult(requestCode, resultCode, data)

    when (requestCode) {
      AppConstants.RC_GOOGLE_SIGN_IN.intKey -> {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data!!)
        handleSignInResunlt(task)
      }
    }
  }

  private fun handleSignInResunlt(completeTask: Task<GoogleSignInAccount>) {
    try {
      val account: GoogleSignInAccount = completeTask.result
      saveToPrefs(account)

      startActivity(Intent(applicationContext,MainScreenActivityJ::class.java))
      finishAffinity()
    } catch (e: ApiException) {
      Log.w(TAG, "signInResult:failed code=" + e.statusCode)
    }
  }

  private fun saveToPrefs(account: GoogleSignInAccount) {
    with(account) {
      with(prefs) {
        userInJson = User(email!!, displayName, familyName, givenName, photoUrl.toString())
        userLoggedIn = true
        userEmail = email!!
        userDisplayName = displayName!!
        userFamilyName = familyName!!
        userGivenName = givenName!!
        userPhotoUrl = photoUrl.toString()!!
      }
    }
  }

  private fun showSignUpFragment():Unit {
    // Compiling :-)
    //val signUpFragment: SignUpFragmentJ by lazy { SignUpFragmentJ.newInstance(null) }
    val signUpFragment: SignUpFragmentJ = SignUpFragmentJ.newInstance(null)
    val manager: FragmentManager = supportFragmentManager
    signUpFragment.show(manager, "SignUpFragmentJ")
  }
}
