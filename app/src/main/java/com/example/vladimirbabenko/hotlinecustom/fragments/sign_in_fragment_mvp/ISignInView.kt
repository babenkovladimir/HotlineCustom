package com.example.vladimirbabenko.hotlinecustom.fragments.sign_in_fragment_mvp

import com.example.vladimirbabenko.hotlinecustom.base.mvp.IView

interface ISignInView:IView {

  fun setErrors(emailError: String?, passwordError: String?)
  fun startMainScreen()




}