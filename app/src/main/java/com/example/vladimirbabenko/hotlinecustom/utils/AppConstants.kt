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
  USER_FHOTO_URL("USER_FHOTO_URL"),
  USER_FROM_JSON("USER_FROM_JSON"),

  // Cashe key
  CASHE_NOTEBOOK_PREF_KEY("CASHE_NOTEBOOK_PREF_KEY"),
  CASH_NOTEBOOK_JSON_KEY("CASH_NOTEBOOK_JSON_KEY"),

  IS_INTERNET_CONNECTED_KEY("IS_INTERNET_CONNECTED_KEY");

}