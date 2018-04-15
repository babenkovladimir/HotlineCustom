package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import com.example.vladimirbabenko.hotlinecustom.base.mvp.IView
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart

interface ICarPartsView:IView {

  fun showPartList(partList: List<CarPart>)
  fun handleSingleClick(position: Int?, carPart: CarPart)
}