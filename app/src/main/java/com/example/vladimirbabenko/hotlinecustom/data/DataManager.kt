package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.App
import com.example.vladimirbabenko.hotlinecustom.data.cashe.BaseCashe
import com.example.vladimirbabenko.hotlinecustom.data.cashe.CasheCarPart
import com.example.vladimirbabenko.hotlinecustom.data.cashe.CasheNotebookJ
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockCarParts
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockNoteBookS
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants

class DataManager private constructor(context: Context) {

  val prefs: PreferencesHelper = PreferencesHelper(context)
  private val mRepositoryMockNoteBookS = RepositoryMockNoteBookS()
  private val mRepositoryMockCarParts = RepositoryMockCarParts()

  private val casheNoteBook: CasheNotebookJ =
    CasheNotebookJ(context, AppConstants.CASHE_NOTEBOOK_PREF_KEY.key,
      AppConstants.CASH_NOTEBOOK_JSON_KEY.key)

  private val casheCarPart = CasheCarPart(context, AppConstants.CASHE_CAR_PART_PREFS_KEY.key,
    AppConstants.CASH_CAR_PART_JSON_KEY.key)

  private val bascketHelper =
    BascketHelper(context, AppConstants.BASCKET_PREFS_KEY.key, AppConstants.BASCKET_JSON_KEY.key)

  init {
    Log.d("TAG", "DataMandger is Created in companion object")
  }

  companion object {
    val create: DataManager by lazy { DataManager(App.applicationContext()) }
  }

  fun fetchMocks(): List<NoteBook> = mRepositoryMockNoteBookS.fetchMocks()

  fun fetchCarMocks(): List<CarPart> = mRepositoryMockCarParts.fetchMocks()

  fun getCasheNotebook() = casheNoteBook.getList()
  fun saveCasheNoteBook(list: List<NoteBook>) = casheNoteBook.saveList(list)

  fun getCasheCarPart() = casheCarPart.getList()
  fun saveCasheCarPart(list: List<CarPart>) = casheCarPart.saveList(list)

  fun addBascket(id: BascketItem) = bascketHelper.put(id)
  fun getFromBasket():MutableSet<BascketItem> = bascketHelper.get()
  fun clearBascket() = bascketHelper.clearBascket()
}





