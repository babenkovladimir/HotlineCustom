package com.example.vladimirbabenko.hotlinecustom.utils

import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard

class VideoCardMapper : BaseMapper<VideoCard, BascketItem> {

  override fun transform(input: VideoCard) =
    BascketItem(id = input.id, name = input.name, price = input.price, photoUrl = input.photoUrl, num = 1)
}