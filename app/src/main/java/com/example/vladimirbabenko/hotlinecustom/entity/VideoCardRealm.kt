package com.example.vladimirbabenko.hotlinecustom.entity

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class VideoCardRealm(
  @Required @PrimaryKey  var id: String? = "",
  var name: String? = "",
  var memorySize: Int? = null,
  var price: Int? = null,
  var photoUrl: String? = null,
  var description: String? = null
): RealmObject()