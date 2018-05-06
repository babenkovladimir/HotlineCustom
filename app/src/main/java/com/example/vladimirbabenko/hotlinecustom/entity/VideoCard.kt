package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class VideoCard(
  @PrimaryKey var id: Int? = 0,
  var name: String? = "",
  var memorySize: Int? = 0,
  var price: Int? = 0,
  var photoUrl: String? = "",
  var description: String? = "") : RealmObject(), Parcelable {
  constructor(parcel: Parcel) : this(parcel.readValue(Int::class.java.classLoader) as? Int,
    parcel.readString(), parcel.readValue(Int::class.java.classLoader) as? Int,
    parcel.readValue(Int::class.java.classLoader) as? Int, parcel.readString(),
    parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(name)
    parcel.writeValue(memorySize)
    parcel.writeValue(price)
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