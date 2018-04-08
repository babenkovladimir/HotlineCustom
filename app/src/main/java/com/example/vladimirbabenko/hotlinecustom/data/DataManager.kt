package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED

class DataManager private constructor(context: Context) {

  init {
    Log.d("TAG","DataManager init")
    //var prefs: PreferencesHelper by lazy(SYNCHRONIZED) { PreferencesHelper(context) }
  }


  val TAG = this.javaClass.simpleName
  val prefs:PreferencesHelper = PreferencesHelper(context)
  val mRepositoryMockNoteBookS = RepositoryMockNoteBookS()

  companion object {
    fun create(context: Context):DataManager{
      Log.d("TAG", "DataMandger is Created in companion object")
      return DataManager(context)
    }
  }


  fun fetchMocks(): List<NoteBook> = mRepositoryMockNoteBookS.fetchMocks()





  //  init {
  //    Log.d("TAG", "DataManager initializing")
  //  }
  //
  //  private object Holder {
  //    init {
  //
  //      Log.d("TAG" , "DataManager.Holder Initialization")
  //    }
  //
  //    val INSTANCE = DataManager()
  //  }
  //
  //  companion object Factory {
  //    val instance: DataManager by lazy { Holder.INSTANCE }
  //  }
}





