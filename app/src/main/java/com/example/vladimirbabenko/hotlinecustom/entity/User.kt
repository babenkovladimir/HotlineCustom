package com.example.vladimirbabenko.hotlinecustom.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class User(val id: String,  val email: String, var displayedName: String? = "DisplayedName",
  var familyName: String? = "familyName", var givenName: String? = "givenName",
  var fotoUrl: String? = "emptyUrl") : Parcelable {
  constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(), parcel.readString(),
    parcel.readString(), parcel.readString(), parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(email)
    parcel.writeString(displayedName)
    parcel.writeString(familyName)
    parcel.writeString(givenName)
    parcel.writeString(fotoUrl)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<User> {
    override fun createFromParcel(parcel: Parcel): User {
      return User(parcel)
    }

    override fun newArray(size: Int): Array<User?> {
      return arrayOfNulls(size)
    }
  }
}