package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class VideoCard(val id: Int, val name: String, val memorySize: Int, val price: Int, val photoUrl: String, val description: String) :
  Parcelable {
  constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readInt(),
    parcel.readInt(), parcel.readString(), parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeInt(memorySize)
    parcel.writeInt(price)
    parcel.writeString(photoUrl)
    parcel.writeString(description)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<VideoCard> {
    override fun createFromParcel(parcel: Parcel): VideoCard {
      return VideoCard(parcel)
    }

    override fun newArray(size: Int): Array<VideoCard?> {
      return arrayOfNulls(size)
    }
  }
}