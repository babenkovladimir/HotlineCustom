package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.entity.User
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.google.gson.Gson

class PreferencesHelper(context: Context) : BasePreferencesHelper(context) {

  init {}

  fun clearUserPreferences() {
    userId = ""
    userLoggedIn = false
    userEmail = ""
    userFamilyName = ""
    userGivenName = ""
    userDisplayName = ""
    userPhotoUrl = ""
  }

  var userLoggedIn: Boolean
    get() = getBoolean(AppConstants.IS_USER_LOGGED.key)
    set(value) = setBoolean(AppConstants.IS_USER_LOGGED.key, value)

  var userEmail: String
    get() = getString(AppConstants.USER_EMAIL.key)
    set(value) = setString(AppConstants.USER_EMAIL.key, value)

  var userDisplayName: String
    get() = getString(AppConstants.USER_DISPLAYED_NAME.key)
    set(value) = setString(AppConstants.USER_DISPLAYED_NAME.key, value)

  var userGivenName: String
    get() = getString(AppConstants.USER_GIVEN_NAME.key)
    set(value) = setString(AppConstants.USER_GIVEN_NAME.key, value)

  var userFamilyName: String
    get() = getString(AppConstants.USER_FAMILY_NAME.key)
    set(value) = setString(AppConstants.USER_FAMILY_NAME.key, value)

  var userPhotoUrl: String
    get() = getString(AppConstants.USER_FHOTO_URL.key)
    set(value) = setString(AppConstants.USER_FHOTO_URL.key, value)

  var userId: String
  get() = getString(AppConstants.USER_ID.key)
  set(value) = setString(AppConstants.USER_ID.key, value)

  //TODO How to create null user?
  var userInJson: User
    get() =  Gson().fromJson(getString(AppConstants.USER_FROM_JSON.key), User::class.java)
    set(user) {
      val userString: String = Gson().toJson(user)
      setString(AppConstants.USER_FROM_JSON.key, userString)
    }

  var withInternetConnection:Boolean
  get() = getBoolean(AppConstants.IS_INTERNET_CONNECTED_KEY.key)
  set(value) = setBoolean(AppConstants.IS_INTERNET_CONNECTED_KEY.key, value)

  var bascketSize:Int = getInt(AppConstants.BASKETSIZE.key)
  get
  fun modifyBascketSize(value: Int) = setInt(AppConstants.BASKETSIZE.key, bascketSize+value)


}

