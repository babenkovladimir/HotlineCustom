package com.example.vladimirbabenko.hotlinecustom.data

class DataManager private constructor(){

  private object DataManagerHolder { val INSTANCE = DataManager() }

companion object {

  val getInstanse:DataManager by lazy { DataManagerHolder.INSTANCE }
  }



}


