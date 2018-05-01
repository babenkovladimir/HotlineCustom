package com.example.vladimirbabenko.hotlinecustom.data

import com.example.vladimirbabenko.hotlinecustom.entity.UserRealm
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import io.realm.Realm

class RealmHelper {

  private var mRealm: Realm

  init {
    mRealm = Realm.getDefaultInstance()
  }

  fun saveUserRealm(userRealm: UserRealm) {
    clearUser()
    mRealm.executeTransaction(object : Realm.Transaction {
      override fun execute(realm: Realm) {
        realm.insertOrUpdate(userRealm)
      }
    })
  }

  fun getUser(): UserRealm? {
    var userRealm: UserRealm? =
      mRealm.where(UserRealm::class.java).equalTo("id", AppConstants.REALM_USER_ID.key).findFirst()
    return userRealm
  }

  // Clear current user from DB
  fun clearUser() {
    var realmResult = mRealm.where(UserRealm::class.java).findAll()
    mRealm.executeTransaction(object : Realm.Transaction {
      override fun execute(realm: Realm?) {
        realmResult.deleteAllFromRealm()
      }
    })
  }
}