package com.example.vladimirbabenko.hotlinecustom.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import com.example.vladimirbabenko.hotlinecustom.MainScreenActivity
import com.example.vladimirbabenko.hotlinecustom.R.layout
import com.example.vladimirbabenko.hotlinecustom.TermsActivity
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.entity.User
import com.example.vladimirbabenko.hotlinecustom.entity.UserRealm
import com.example.vladimirbabenko.hotlinecustom.fragments.SignUpFragment
import com.example.vladimirbabenko.hotlinecustom.login.sign_in_fragment_mvp.SignInFragment
import com.example.vladimirbabenko.hotlinecustom.j.MainScreenActivityJ
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.btSignIn
import kotlinx.android.synthetic.main.activity_login.btSignInGoogleButton
import kotlinx.android.synthetic.main.activity_login.btSignUp
import kotlinx.android.synthetic.main.activity_login.btTerms

class LoginActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_login)

    setupUI()
  }

  /*
  * Setup listners to buttons
  * */
  private fun setupUI() {
    btSignInGoogleButton.setOnClickListener(object : View.OnClickListener {
      override fun onClick(p0: View?) {
        googleSignIn()
      }
    })
    btSignInGoogleButton.setSize(SignInButton.SIZE_WIDE)
    btSignUp.setOnClickListener({ showSignUpFragment() })


    btSignIn.setOnClickListener() {
      val manager = supportFragmentManager
      val fragment = SignInFragment.newInstance(null)
      fragment.show(manager, "SignInFragment")
    }
    btTerms.setOnClickListener({
      startActivity(Intent(applicationContext, TermsActivity::class.java))
    })
  }

  /*
  * Not using now
  * */
  private fun checkForexitingSignedInUser(): Unit {
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

    if (account != null) {
      with(GoogleSignIn.getLastSignedInAccount(this)!!) {
        logd(email!!)
        logd(displayName!!)
        logd(photoUrl.toString()!!)
      }
    } else logd("User is S I G N E D   O U T")
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
    if (completeTask.isSuccessful)
    try {
      val account: GoogleSignInAccount = completeTask.result
      saveToPrefs(account)
      dataManager.synhronizeVithCloud()
      startActivity(Intent(applicationContext, MainScreenActivity::class.java))
      finishAffinity()
    } catch (e: ApiException) {
      Log.w(TAG, "signInResult:failed code=" + e.statusCode)
    }
  }

  /*
  *   This fun saves user three times
  *     - to preferences by fields
  *     - to preferences by json
  *     - to Realm DB
  * */
  private fun saveToPrefs(account: GoogleSignInAccount) {
    with(account) {
      with(dataManager.prefs) {
        userInJson =
            User(id.toString(), email!!, displayName, familyName, givenName, photoUrl.toString())

        userLoggedIn = true
        userEmail = email!!
        userDisplayName = displayName!!
        userFamilyName = familyName!!
        userGivenName = givenName!!
        userPhotoUrl = photoUrl.toString()!!

        val userRealm =
          UserRealm(AppConstants.REALM_USER_ID.intKey, id.toString(), email!!, displayName,
            familyName, givenName, photoUrl.toString())

        // Using RealmObject
        dataManager.setUser(userRealm)
      }

    }
  }

  private fun showSignUpFragment(): Unit { // Compiling :-)
    //val signUpFragment: SignUpFragmentJ by lazy { SignUpFragmentJ.newInstance(null) }
    val signUpFragment = SignUpFragment.newInstance()
    val manager: FragmentManager = supportFragmentManager
    signUpFragment.show(manager, "SignUpFragment")
  }
}
