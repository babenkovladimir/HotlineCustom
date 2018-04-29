package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

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
    getView()?.showPartList(dataManager.getCasheCarPart(), getChosenList())
  }

  fun onItemClicked(position: Int) {
    val carPart = dataManager.getCasheCarPart().get(position)
    getView()?.handleSingleClick(position, carPart, getChosenList().contains(carPart.id))
  }

  fun getChosenList(): MutableList<Int>{
    return dataManager.getChosenList()
  }

}