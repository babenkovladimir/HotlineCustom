package com.example.vladimirbabenko.hotlinecustom.data

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseCloudHelper<T>(userId: String) {

  var database: FirebaseDatabase
  var ref: DatabaseReference

  init {
    database = FirebaseDatabase.getInstance()
    ref = database.reference
    Log.d("TAG_Firebase", "FirebaseDBHelper = " + this)
  }


}