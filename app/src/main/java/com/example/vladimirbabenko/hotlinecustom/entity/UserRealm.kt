package com.example.vladimirbabenko.hotlinecustom.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass open class UserRealm

  constructor(
    @Required @PrimaryKey var id: String = "",
    @Required var userId: String = "",
    @Required var email: String? = "",
    var displayedName: String? = null,
    var familyName: String? = null,
    var givenName: String? = null,
    var fotoUrl: String? = null) : RealmObject()