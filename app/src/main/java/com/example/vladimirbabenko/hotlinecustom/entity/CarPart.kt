package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CarPart(
  @PrimaryKey var id: Int? = 0,
  var name:String? = "",
  var partPrice:Int? = 0,
  var partPhotoUrl:String? = "",
  var description:String? = "") : RealmObject(), Parcelable {
  constructor(parcel: Parcel) : this(parcel.readValue(Int::class.java.classLoader) as? Int,
    parcel.readString(), parcel.readValue(Int::class.java.classLoader) as? Int, parcel.readString(),
    parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(name)
    parcel.writeValue(partPrice)
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