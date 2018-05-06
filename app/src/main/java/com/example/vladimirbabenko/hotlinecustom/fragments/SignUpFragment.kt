package com.example.vladimirbabenko.hotlinecustom.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.MainScreenActivity
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.User
import com.example.vladimirbabenko.hotlinecustom.entity.UserRealm
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.REALM_USER_ID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.fragment_sign_in.etFragmentSignInEmail
import kotlinx.android.synthetic.main.fragment_sign_up.btFragmentSignUp
import kotlinx.android.synthetic.main.fragment_sign_up.etFragmentSignUpEmail
import kotlinx.android.synthetic.main.fragment_sign_up.etFragmentSignUpPassword
import kotlinx.android.synthetic.main.fragment_sign_up.etFragmentSignUpUserName

class SignUpFragment : DialogFragment() {

  private var dataManager: DataManager
  private var mAuth: FirebaseAuth

  companion object {
    fun newInstance(bundle: Bundle? = null): SignUpFragment {
      val fragment = SignUpFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  init {
    dataManager = DataManager.create
    mAuth = FirebaseAuth.getInstance()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_sign_up, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupUI()
  }

  // Private Helpers

  private fun setupUI() {
    isCancelable = true

    btFragmentSignUp.setOnClickListener(object : View.OnClickListener {
      override fun onClick(v: View?) {
        var emailError = false
        var passwordError = false
        var nameError = false

        if (!isValidEmail(etFragmentSignUpEmail.getText())) {
          emailError = true
          etFragmentSignUpEmail.setError("email is not valid")
        } else {
          emailError = false
          etFragmentSignUpEmail.setError(null)
        }

        if (!isValidPassword(etFragmentSignUpPassword.getText())) {
          passwordError = true
          etFragmentSignUpPassword.setError("password is short")
        } else {
          passwordError = false
          etFragmentSignUpPassword.setError(null)
        }

        if (!isValidUserName(etFragmentSignUpUserName.getText())) {
          nameError = true
          etFragmentSignUpUserName.setError("name is short")
        } else {
          nameError = false
          etFragmentSignUpUserName.setError(null)
        }

        if (!emailError && !passwordError && !nameError) {
          Toast.makeText(context, "User is valid can continue", Toast.LENGTH_LONG).show()

          mAuth.createUserWithEmailAndPassword(etFragmentSignUpEmail.getText().toString(),
            etFragmentSignUpPassword.getText().toString())
            .addOnCompleteListener(activity!!) { task ->
              if (task.isSuccessful) {
                signInUser(etFragmentSignUpEmail.getText().toString(),
                  etFragmentSignUpPassword.getText().toString())
              }
            }.addOnFailureListener(activity!!) { e -> showErrorDialog(e.message.toString()) }
        }
      }
    })
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

  private fun isValidUserName(name: CharSequence): Boolean {
    return if (name.length > 4) true else false
  }

  fun signInUser(email: String, password: String) {
    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity!!) { task ->
      if (task.isSuccessful) {
        val fireUser = mAuth.currentUser

        val profileUpdates = UserProfileChangeRequest.Builder()
          .setDisplayName(etFragmentSignUpUserName.getText().toString()).build()

        // Update user profile and callback
        fireUser!!.updateProfile(profileUpdates).addOnCompleteListener { task ->
          if (task.isSuccessful) {
            if (saveUserInfoToPreferences(fireUser)) {
              startActivity(Intent(activity, MainScreenActivity::class.java))
              dismiss()
            }
          }
        }
      } else {
        Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show()
        dismiss()
      }
    }
  }

  private fun saveUserInfoToPreferences(fireUser: FirebaseUser): Boolean {
    val id = fireUser.uid
    val email = fireUser.email
    val displayedName = fireUser.displayName
    val givenName: String? = null
    val familyName: String? = null
    val photoUrl = fireUser.photoUrl.toString()
    val user = User(id, email!!, displayedName, familyName, givenName, photoUrl)

    dataManager.prefs.userEmail = email
    dataManager.prefs.userInJson = user
    dataManager.prefs.userLoggedIn = true
    dataManager.prefs.userByFireBase = true

    val userRealm =
      UserRealm(REALM_USER_ID.intKey, id, email, displayedName, familyName, givenName, photoUrl)
    dataManager.setUser(userRealm)

    return true
  }

  private fun showErrorDialog(message: String) {
    val builder = AlertDialog.Builder(activity)
    val dialog = builder.setTitle("Warning").setMessage(message).setCancelable(false)
      .setPositiveButton("Ok") { dialog, which -> dismiss() }.create()
    dialog.show()
  }
}