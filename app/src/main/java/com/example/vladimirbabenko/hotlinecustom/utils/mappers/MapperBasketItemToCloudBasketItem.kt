package com.example.vladimirbabenko.hotlinecustom.utils.mappers

import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.CloudBasketItem

class MapperBasketItemToCloudBasketItem : BaseMapper<BascketItem, CloudBasketItem> {
  override fun transform(input: BascketItem): CloudBasketItem {
    return CloudBasketItem(input.id, input.name, input.price, input.photoUrl, input.num)
  }
}