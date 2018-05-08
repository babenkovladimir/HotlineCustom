package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.mvp.BasePresenter
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

class BascketPresenter() : BasePresenter<BascketActivity>() {

  val dataManager = DataManager.create

  init {
    Log.d("TAGBASKET", "BascketPresenter initializing")
  }

  fun fetchMocks() {
    Log.d("BascketPresenter", dataManager.getFromBasket().toString())
    getView()?.showList(dataManager.getFromBasket())
  }

  fun update(deltaValue: Int, position: Int) {
    val items = dataManager.getFromBasket()
    val basketItemToUpdate = items.elementAt(position)
    dataManager.updateBasketItemCountValue(basketItemToUpdate, deltaValue)
    dataManager.saveBasketItemsToFirebase()
    dataManager.saveBasketItemsToFirebase()
    getView()?.showList(dataManager.getFromBasket())
  }
}