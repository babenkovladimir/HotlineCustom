package com.example.vladimirbabenko.hotlinecustom.data

import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.CloudBasketItem
import com.example.vladimirbabenko.hotlinecustom.utils.mappers.MapperCloudBascketItemToBAsketItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.TimeUnit

class FirebaseDBHelper(val userId: String) {

  var database: FirebaseDatabase
  var ref: DatabaseReference

  init {
    Log.d("TAGFIREBASEHELPER", "FirebaseDBHelper = " + this)
    database = FirebaseDatabase.getInstance()
    ref = database.reference //    dataManager = DataManager.create
  }

  fun saveUserName(userName: String) {
    ref.child("users".toString()).child(userId).child("userName".toString()).setValue(userName)
  }

  fun saveChosenList(chosenList: List<CloudBasketItem>) {
    ref.child("users".toString()).child(userId).child("chosenList".toString()).setValue(chosenList)
  }

  fun saveBasketItemsToCloud(list: List<CloudBasketItem>) {
    ref.child("users".toString()).child(userId).child("chosenList".toString()).setValue(list)
  }

  fun getListCloudBascketItems(): List<CloudBasketItem>? {

    var list: List<CloudBasketItem>? = null
    var listBasketItems = mutableListOf<BascketItem>()

    ref.child("users".toString()).child(userId).child("chosenList".toString())
      .addValueEventListener(object : ValueEventListener {
        override fun onDataChange(
          dataSnapshot: DataSnapshot?) { // It fails if use List<> instead ArrayList<>
          val typeToken = object : GenericTypeIndicator<ArrayList<CloudBasketItem>>() {}

          list = dataSnapshot?.getValue(typeToken)

          list?.forEach { item ->
            listBasketItems.add(
              MapperCloudBascketItemToBAsketItem().transform(item)) // This is kostul

            Log.d("TAGLISTBASKET", listBasketItems.toString())
          }
        }

        override fun onCancelled(p0: DatabaseError?) {
        }
      })


    return null
  }
}