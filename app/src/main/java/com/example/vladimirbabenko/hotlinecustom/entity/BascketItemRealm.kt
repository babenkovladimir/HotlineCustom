package com.example.vladimirbabenko.hotlinecustom.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass open class BascketItemRealm(
    @PrimaryKey var id: String? = "",
    @Required var name : String ? = "",
    var price : Int?  = 1,
    var photoUrl : String? = "",
    var num:Int? = 1) : RealmObject()