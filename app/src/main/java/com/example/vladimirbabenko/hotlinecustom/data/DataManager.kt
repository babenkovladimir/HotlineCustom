package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.App
import com.example.vladimirbabenko.hotlinecustom.data.cashe.BaseCashe
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockNoteBookS
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants

class DataManager private constructor(context: Context) {

  val TAG = this.javaClass.simpleName
  val prefs: PreferencesHelper = PreferencesHelper(context)
  val mRepositoryMockNoteBookS = RepositoryMockNoteBookS()
  val casheNoteBook = BaseCashe<NoteBook, NoteBook>(context, AppConstants.CASHE_NOTEBOOK_PREF_KEY.key, AppConstants.CASH_NOTEBOOK_JSON_KEY.key)


  companion object {
    fun create(): DataManager {
      Log.d("TAG", "DataMandger is Created in companion object")
      val context:Context by lazy { App.applicationContext() }
      return DataManager(context)
    }
  }

  fun fetchMocks(): List<NoteBook> = mRepositoryMockNoteBookS.fetchMocks()

  fun getCasheNotebook() = casheNoteBook.getList()

  fun saveCasheNoteBook(vararg list: List<NoteBook>) {
    casheNoteBook.saveList(*list)
  }

}





