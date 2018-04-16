package com.example.vladimirbabenko.hotlinecustom.entity

data class NoteBook(val id:Int , val brand:String, val model:String, val price: Int, val processor: String, val videoCard: String,  val hdd: String, val photUrl:String?="" ){

  override fun toString(): String {
    return "Brand: $brand, model: $model, price: $price, processor: $processor, videoCard: $videoCard, hdd: $hdd, photo: $photUrl"
  }
}