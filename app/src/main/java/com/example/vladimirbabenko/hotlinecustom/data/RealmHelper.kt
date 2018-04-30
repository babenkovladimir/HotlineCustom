package com.example.vladimirbabenko.hotlinecustom.data

import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.entity.User
import com.example.vladimirbabenko.hotlinecustom.entity.UserRealm
import io.realm.Realm

class RealmHelper {

  lateinit var realm: Realm

  init {
    realm = Realm.getDefaultInstance()
    clearDB()
    Log.d("TAGREALMINSTANCE", realm.hashCode().toString())
  }

  fun saveUserRealm(user: User) {
    val userRealm = realm.createObject(UserRealm::class.java, "currentUser")

    userRealm.userId = user.id
    userRealm.email = user.email
    userRealm.displayedName = user.displayedName
    userRealm.givenName = user.givenName
    userRealm.familyName = user.familyName
    userRealm.fotoUrl = user.fotoUrl
    Log.d("TAGUSERREQLM", "user realm = " + userRealm)

    realm.executeTransaction(object : Realm.Transaction {
      override fun execute(realm: Realm?) {
        realm?.insertOrUpdate(userRealm)
      }
    })
  }

  fun getUser(): UserRealm? {
    var userRealm: UserRealm? =
      realm.where(UserRealm::class.java).equalTo("id", "currentUser").findFirst()
    Log.d("TAGUSERREQLM", "user realm from read = " + userRealm)
    return userRealm
  }

  fun clearDB() {
    realm.beginTransaction()
    realm.deleteAll()
    realm.commitTransaction()
  }
}