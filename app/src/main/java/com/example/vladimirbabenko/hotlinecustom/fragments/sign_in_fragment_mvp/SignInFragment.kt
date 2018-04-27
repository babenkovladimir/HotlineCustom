package com.example.vladimirbabenko.hotlinecustom.fragments.sign_in_fragment_mvp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.MainScreenActivity
import com.example.vladimirbabenko.hotlinecustom.R
import kotlinx.android.synthetic.main.fragment_sign_in.btFragmentSignIn
import kotlinx.android.synthetic.main.fragment_sign_in.view.etFragmentSignInEmail
import kotlinx.android.synthetic.main.fragment_sign_in.view.etFragmentSignInPassword

class SignInFragment : DialogFragment(), ISignInView {

  val presenter: SignInPresenter = SignInPresenter()

  companion object {
    fun newInstance(bundle: Bundle?): SignInFragment {
      val fragment = SignInFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter.bind(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_sign_in, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupUI(view, savedInstanceState)
  }

  override fun onDestroy() {
    presenter.unbind()
    super.onDestroy()
  }

  // Private helpers

  private fun setupUI(view: View, savedInstanceState: Bundle?) {

    btFragmentSignIn.setOnClickListener(object : View.OnClickListener {
      override fun onClick(v: View?) {
        presenter.signIn(view.etFragmentSignInEmail?.text.toString(),
          view.etFragmentSignInPassword?.text.toString())
      }
    })
  }

  // MVP implementation

  override fun setErrors(emailError: String?, passwordError: String?) {
    getView()?.etFragmentSignInEmail?.error = emailError
    getView()?.etFragmentSignInPassword?.error =  passwordError
  }
  override fun startMainScreen() {
    dismiss()
   startActivity(Intent(context, MainScreenActivity::class.java))
  }

}


