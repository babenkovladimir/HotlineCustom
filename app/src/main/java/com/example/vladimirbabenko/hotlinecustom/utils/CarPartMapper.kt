package com.example.vladimirbabenko.hotlinecustom.utils

import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart

class CarPartMapper:BaseMapper<CarPart, BascketItem> {
  override fun transform(input: CarPart): BascketItem{
    return BascketItem(input.id, input.name, input.partPrice, input.partPhotoUrl.toString())
  }
}