package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.net.Uri
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants

class PreferencesHelper(context: Context) : BasePreferencesHelper(context) {

  fun clearUserPreferences(){
    setIsUserLoggedIn(false)
    setUserEmail("")
    setUserFamilyName("")
    setUserGivenName("")
    setUserDisplayedName("")
    setUserPhotoUrl("")
  }

  fun isUserLoggedIn() = getBoolean(AppConstants.IS_USER_LOGGED.key)
  fun setIsUserLoggedIn(value: Boolean) = setBoolean(AppConstants.IS_USER_LOGGED.key, value)

  fun setUserEmail(email:String) = setString(AppConstants.USER_EMAIL.key, email)
  fun getUserEmail():String = getString(AppConstants.USER_EMAIL.key)

  fun setUserDisplayedName(name:String) = setString(AppConstants.USER_DISPLAYED_NAME.key, name)
  fun getDisplayedUserName():String = getString(AppConstants.USER_DISPLAYED_NAME.key)

  fun setUserGivenName(givenName:String) = setString(AppConstants.USER_GIVEN_NAME.key, givenName)
  fun getUserGivenName():String = getString(AppConstants.USER_GIVEN_NAME.key)

  fun setUserFamilyName(familyName:String) = setString(AppConstants.USER_FAMILY_NAME.key, familyName)
  fun getUserFamilyName() = getString(AppConstants.USER_FAMILY_NAME.key)

  fun setUserPhotoUrl(photoUrl:String) = setString(AppConstants.USER_FHOTO_URL.key, photoUrl)
  fun getUserPhotoUrl() = getString(AppConstants.USER_FHOTO_URL.key)

}