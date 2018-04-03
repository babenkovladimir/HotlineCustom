package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.entity.User
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.google.gson.Gson

class PreferencesHelper(context: Context) : BasePreferencesHelper(context) {

  init {

  }

  fun clearUserPreferences() {
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

  //TODO How to create null user?
  var userInJson: User
    get() = Gson().fromJson(getString(AppConstants.USER_FROM_JSON.key), User::class.java)
    set(user) {
      Log.d("TAG", user.toString())
      var userString: String = Gson().toJson(user)
      Log.d("TAG", userString)
      setString(AppConstants.USER_FROM_JSON.key, userString)
    }
}

