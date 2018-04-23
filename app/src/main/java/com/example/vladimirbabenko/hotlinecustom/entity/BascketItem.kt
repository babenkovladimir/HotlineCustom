package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class BascketItem (val id: Int, val name:String, val price: Int, val photoUrl:String, var num:Int) :
  Parcelable {
  constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readInt(),
    parcel.readString(), parcel.readInt()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeInt(price)
    parcel.writeString(photoUrl)
    parcel.writeInt(num)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<BascketItem> {
    override fun createFromParcel(parcel: Parcel): BascketItem {
      return BascketItem(parcel)
    }

    override fun newArray(size: Int): Array<BascketItem?> {
      return arrayOfNulls(size)
    }
  }
}
