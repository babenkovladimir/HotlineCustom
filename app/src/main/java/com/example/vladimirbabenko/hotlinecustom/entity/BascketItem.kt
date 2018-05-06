package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class BascketItem (
  @PrimaryKey var id: Int? = 0,
  var name:String? = "",
  var price: Int? = 0,
  var photoUrl:String? = "",
  var num:Int? = 0) : RealmObject(), Parcelable {
  constructor(parcel: Parcel) : this(parcel.readValue(Int::class.java.classLoader) as? Int,
    parcel.readString(), parcel.readValue(Int::class.java.classLoader) as? Int, parcel.readString(),
    parcel.readValue(Int::class.java.classLoader) as? Int) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(name)
    parcel.writeValue(price)
    parcel.writeString(photoUrl)
    parcel.writeValue(num)
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
