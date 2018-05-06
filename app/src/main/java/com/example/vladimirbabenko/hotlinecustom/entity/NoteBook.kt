package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class NoteBook(

  @PrimaryKey var id: Int? = 0,
  var brand: String? = "",
  var model: String? = "",
  var price: Int? = 0,
  var processor: String? = "",
  var videoCard: String? = "",
  var hdd: String? = "",
  var photUrl: String? = "",
  var description: String? = "") : RealmObject(), Parcelable {
  constructor(parcel: Parcel) : this(parcel.readValue(Int::class.java.classLoader) as? Int,
    parcel.readString(), parcel.readString(), parcel.readValue(Int::class.java.classLoader) as? Int,
    parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(),
    parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(brand)
    parcel.writeString(model)
    parcel.writeValue(price)
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