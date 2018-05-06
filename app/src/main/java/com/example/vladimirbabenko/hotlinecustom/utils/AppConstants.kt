package com.example.vladimirbabenko.hotlinecustom.utils

internal enum class AppConstants(val key: String, val intKey: Int? = 0) {

  // Int key
  RC_GOOGLE_SIGN_IN("RC_GOOGLE_SIGN_IN", 12345),

  // String key
  APP_PREFERENCES("APP_PREFERENCES"),
  IS_USER_LOGGED("IS_USER_LOGGED"),
  USER_ID("USER_ID"),
  USER_EMAIL("USER_EMAIL"),
  USER_DISPLAYED_NAME("USER_DISPLAYED_NAME"),
  USER_GIVEN_NAME("USER_GIVEN_NAME"),
  USER_FAMILY_NAME("USER_FAMILY_NAME"),
  USER_FHOTO_URL("USER_FHOTO_URL"),
  USER_FROM_JSON("USER_FROM_JSON"),
  USER_FIREBASE_AUTH("USER_FIREBASE_AUTH"),

  // Bundle key
  CAR_PART_BUNDLE("CAR_PART_BUNDLE"),
  NOTEBOOK_PART_BUNDL("NOTEBOOK_PART_BUNDL"),
  VIDEO_CARD_BUNDLE("VIDEO_CARD_BUNDLE"),

  IS_IN_BASKET("IS_IN_BASKET"),

  // Cashe key
  CASHE_NOTEBOOK_PREF_KEY("CASHE_NOTEBOOK_PREF_KEY"),
  CASH_NOTEBOOK_JSON_KEY("CASH_NOTEBOOK_JSON_KEY"),
  CASHE_CAR_PART_PREFS_KEY("CASHE_CAR_PART_PREFS_KEY"),
  CASH_CAR_PART_JSON_KEY("CASH_CAR_PART_JSON_KEY"),
  CASHE_VIDEO_CARD_PREFS_KEY("CASHE_VIDEO_CARD_PREFS_KEY"),
  CASH_VIDEO_CARD_JSON_KEY("CASH_VIDEO_CARD_JSON_KEY"),

  // Basket key
  BASCKET_PREFS_KEY("BASCKET_PREFS_KEY"),
  BASCKET_JSON_KEY("BASCKET_JSON_KEY"),
  BASKETSIZE("BASKETSIZE"),

  IS_INTERNET_CONNECTED_KEY("IS_INTERNET_CONNECTED_KEY"),

  // Realm constants
  REALM_USER_ID("REALM_USER_ID", 119)
}