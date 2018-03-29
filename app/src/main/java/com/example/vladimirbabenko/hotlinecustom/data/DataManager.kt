package com.example.vladimirbabenko.hotlinecustom.data

class DataManager internal constructor(){

  init {
    println("DataMAnager is Initialized!")
  }

  private object DataManagerHolder {
    init {
      println("DataMAnagerHolder initializing")
    }
    val INSTANCE = DataManager() }

companion object {

  val getInstanse:DataManager by lazy { DataManagerHolder.INSTANCE }
  }



}


