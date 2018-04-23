package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class NoteBook(val id: Int, val brand: String, val model: String, val price: Int,
  val processor: String, val videoCard: String, val hdd: String, val photUrl: String? = "",
  val description: String? = "") : Parcelable {

  constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readString(),
    parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(),
    parcel.readString(), parcel.readString()) {
  }

  override fun toString(): String {
    return "Brand: $brand, model: $model, price: $price, processor: $processor, videoCard: $videoCard, hdd: $hdd, photo: $photUrl"
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(brand)
    parcel.writeString(model)
    parcel.writeInt(price)
    parcel.writeString(processor)
    parcel.writeString(videoCard)
    parcel.writeString(hdd)
    parcel.writeString(photUrl)
    parcel.writeString(description)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<NoteBook> {
    override fun createFromParcel(parcel: Parcel): NoteBook {
      return NoteBook(parcel)
    }

    override fun newArray(size: Int): Array<NoteBook?> {
      return arrayOfNulls(size)
    }
  }
}