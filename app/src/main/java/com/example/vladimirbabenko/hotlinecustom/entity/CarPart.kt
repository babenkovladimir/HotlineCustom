package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class CarPart(val id: Int, val name:String, val partPrice:Int, val partPhotoUrl:String?="", val description:String?="") :
  Parcelable {
  constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readInt(),
    parcel.readString(), parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeInt(partPrice)
    parcel.writeString(partPhotoUrl)
    parcel.writeString(description)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<CarPart> {
    override fun createFromParcel(parcel: Parcel): CarPart {
      return CarPart(parcel)
    }

    override fun newArray(size: Int): Array<CarPart?> {
      return arrayOfNulls(size)
    }
  }
}