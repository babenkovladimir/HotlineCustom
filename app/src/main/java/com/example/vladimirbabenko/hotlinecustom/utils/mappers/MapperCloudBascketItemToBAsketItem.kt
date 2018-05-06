package com.example.vladimirbabenko.hotlinecustom.utils.mappers

import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.CloudBasketItem

class MapperCloudBascketItemToBAsketItem : BaseMapper<CloudBasketItem, BascketItem> {

  override fun transform(input: CloudBasketItem): BascketItem {
    return BascketItem(input.id, input.name, input.price, input.photoUrl, input.num)
  }
}