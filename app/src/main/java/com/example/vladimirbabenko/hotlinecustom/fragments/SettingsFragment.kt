package com.example.vladimirbabenko.hotlinecustom.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.BuildConfig
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.example.vladimirbabenko.hotlinecustom.login.LoginActivity
import com.example.vladimirbabenko.hotlinecustom.utils.InternetConnectionHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.squareup.otto.Bus
import kotlinx.android.synthetic.main.fragment_settings.view.btBattaryState
import kotlinx.android.synthetic.main.fragment_settings.view.btSignOutSettingsFragment
import kotlinx.android.synthetic.main.fragment_settings.view.tvBuildVersionSettingsFragment

class SettingsFragment : Fragment() {

  var dataManager: DataManager
  var bus: Bus
  var mAuth: FirebaseAuth

  init {
    dataManager = DataManager.create
    bus = GlobalBus.instance
    mAuth = FirebaseAuth.getInstance()
  }

  companion object {
    fun newInstance(bundle: Bundle? = null): SettingsFragment {
      val fragment = SettingsFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bus.register(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_settings, container, false);
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupUI(view)
  }

  override fun onDestroy() {
    bus.unregister(this)
    super.onDestroy()
  }

  // Private helpers

  fun setupUI(view: View) {
    view.btSignOutSettingsFragment.setOnClickListener() { signOut() }
    view.btBattaryState.setOnClickListener(object : View.OnClickListener {
      override fun onClick(v: View?) {
        Toast.makeText(context, "is internet " + InternetConnectionHelper.isConnection(),
          Toast.LENGTH_SHORT).show()
      }
    })
    view.tvBuildVersionSettingsFragment.text = "Build version = " + BuildConfig.VERSION_CODE
  }

  /*
   *This void cheks for auth source
   */
  fun signOut() {
    if (dataManager.prefs.userByFireBase) {
      mAuth.signOut()
      dataManager.prefs.clearUserPreferences()
      dataManager.clearUser()
      startActivity(Intent(context, LoginActivity::class.java))
    } else {

      val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

      val mGoogleSignInClient = GoogleSignIn.getClient(activity!!, gso)
      mGoogleSignInClient.signOut()
      dataManager.prefs.clearUserPreferences()
      dataManager.clearBascket()
      dataManager.clearUser()
      activity!!.finishAffinity()
      startActivity(Intent(context, LoginActivity::class.java))
    }
  }
}