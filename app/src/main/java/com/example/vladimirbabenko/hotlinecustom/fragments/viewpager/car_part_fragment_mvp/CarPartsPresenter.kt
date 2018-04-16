package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.mvp.BasePresenter
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart

class CarPartsPresenter() : BasePresenter<CarPartsFragment>() {

  val dataManager = DataManager.create
  var partList: List<CarPart> = emptyList()

  fun fetchMocks() {
    if (dataManager.prefs.withInternetConnection) {
      dataManager.saveCasheCarPart(dataManager.fetchCarMocks())
    }

    partList = dataManager.getCasheCarPart()
    Log.d("TAGLIST", partList.toString())
   // partList = dataManager.fetchCarMocks()
    getView()?.showPartList(partList)
  }

  fun onItemClicked(position: Int) {
    getView()?.handleSingleClick(position, partList[position])
  }
}