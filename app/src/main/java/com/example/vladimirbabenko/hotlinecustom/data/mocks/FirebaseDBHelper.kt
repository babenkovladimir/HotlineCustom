package com.example.vladimirbabenko.hotlinecustom.data.mocks

import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.gson.reflect.TypeToken

class FirebaseDBHelper(val userId: String) {

  var database: FirebaseDatabase
  var ref: DatabaseReference // var userId: String //var dataManager: DataManager

  init {
    Log.d("TAGFIREBASEHELPER", "FirebaseDBHelper = " + this)
    database = FirebaseDatabase.getInstance()
    ref = database.reference //    dataManager = DataManager.create
    //  userId = dataManager.getUser()?.userId.toString()
  }

  fun saveStringToDb() {
    ref.child("users".toString()).child(userId).child("someData".toString())
      .setValue("MyFirstTestDB")
  }

  fun saveChosenList(chosenList: List<Int>) {
    ref.child("users".toString()).child(userId).child("chosenList".toString()).setValue(chosenList)
  }

  fun getChosenList(): List<Int>? {
    var list: List<Int>? = emptyList<Int>()
    ref.child("users".toString()).child(userId).child("chosenList".toString())
      .addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot?) {
          val typeToken = GenericTypeIndicator<List<Int>>()
          list =
              dataSnapshot?.child("users".toString())?.child(userId)?.child("chosenList".toString())
                ?.getValue(typeToken)
        }

        override fun onCancelled(p0: DatabaseError?) {
        }
      })
    return list
  }
}