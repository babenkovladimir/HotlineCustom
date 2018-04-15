package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.App
import com.example.vladimirbabenko.hotlinecustom.data.cashe.BaseCashe
import com.example.vladimirbabenko.hotlinecustom.data.cashe.BaseCasheJ
import com.example.vladimirbabenko.hotlinecustom.data.cashe.CasheNotebookJ
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockCarParts
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockNoteBookS
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASHE_NOTEBOOK_PREF_KEY
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASH_NOTEBOOK_JSON_KEY

class DataManager private constructor(context: Context) {

  val prefs: PreferencesHelper = PreferencesHelper(context)
  private val mRepositoryMockNoteBookS = RepositoryMockNoteBookS()
  private val mRepositoryMockCarParts = RepositoryMockCarParts()

  val casheNoteBook: CasheNotebookJ =
    CasheNotebookJ(context, CASHE_NOTEBOOK_PREF_KEY.key, CASH_NOTEBOOK_JSON_KEY.key)

    init {
      Log.d("TAG", "DataMandger is Created in companion object")
    }

  companion object {
    val create: DataManager by lazy { DataManager(App.applicationContext()) }
  }

  fun fetchMocks(): List<NoteBook> = mRepositoryMockNoteBookS.fetchMocks()

  fun fetchCarMocks(): List<CarPart> = mRepositoryMockCarParts.fetchMocks()

  fun getCasheNotebook() = casheNoteBook.getList()
  fun saveCasheNoteBook(list:List<NoteBook>) =  casheNoteBook.saveList(list)



}





