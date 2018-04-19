package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.mvp.BasePresenter
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

class BascketPresenter():BasePresenter<BascketActivity>(){

  val dataManager = DataManager.create

  init {
    Log.d("TAGBASKET", "BascketPresenter initializing")
  }

  fun fetchMocks(){
    // Create bascket wrapper

    //getView().show
  }


}