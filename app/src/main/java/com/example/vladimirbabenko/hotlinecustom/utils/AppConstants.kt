package com.example.vladimirbabenko.hotlinecustom.utils

internal enum class AppConstants(val key: String, val intKey: Int? = 0) {

  // Int key
  RC_GOOGLE_SIGN_IN("RC_GOOGLE_SIGN_IN", 12345),

  // String key
  APP_PREFERENCES("APP_PREFERENCES"),
  IS_USER_LOGGED("IS_USER_LOGGED"),
  USER_EMAIL("USER_EMAIL"),
  USER_DISPLAYED_NAME("USER_DISPLAYED_NAME"),
  USER_GIVEN_NAME("USER_GIVEN_NAME"),
  USER_FAMILY_NAME("USER_FAMILY_NAME"),
  USER_FHOTO_URL("USER_FHOTO_URL");
}