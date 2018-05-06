package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.App
import com.example.vladimirbabenko.hotlinecustom.data.cashe.CasheCarPart
import com.example.vladimirbabenko.hotlinecustom.data.cashe.CasheNotebookJ
import com.example.vladimirbabenko.hotlinecustom.data.cashe.CasheVideoCard
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockCarParts
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockNoteBookS
import com.example.vladimirbabenko.hotlinecustom.data.mocks.RepositoryMockVidoCard
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.entity.CloudBasketItem
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.entity.UserRealm
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED

class DataManager private constructor(context: Context) {

  val prefs: PreferencesHelper = PreferencesHelper(context)
  private val mRepositoryMockNoteBookS = RepositoryMockNoteBookS()
  private val mRepositoryMockCarParts = RepositoryMockCarParts()
  private val mRepositoryMockVidoCard = RepositoryMockVidoCard()

  // Realm Helper class - мой хелпер класс
  private val realmHelper = RealmHelper()

  // Realm DbHelper from Nikita

  private val realmDbHelper: RealmDbHelper = RealmDbHelper()

  // FirebaseDB

  private val firebaseHelper: FirebaseDBHelper by lazy {
    FirebaseDBHelper(getUser()!!.userId)
  }

  private val casheNoteBook: CasheNotebookJ =
    CasheNotebookJ(context, AppConstants.CASHE_NOTEBOOK_PREF_KEY.key,
      AppConstants.CASH_NOTEBOOK_JSON_KEY.key)

  private val casheCarPart: CasheCarPart by lazy(SYNCHRONIZED) {
    CasheCarPart(context, AppConstants.CASHE_CAR_PART_PREFS_KEY.key,
      AppConstants.CASH_CAR_PART_JSON_KEY.key)
  }

  private val casheVideoCard: CasheVideoCard =
    CasheVideoCard(context = context, PREFS_KEY = AppConstants.CASHE_VIDEO_CARD_PREFS_KEY.key,
      jsonKey = AppConstants.CASH_VIDEO_CARD_JSON_KEY.key)

  private val bascketHelper: BascketHelper by lazy(SYNCHRONIZED) {
    BascketHelper(context, AppConstants.BASCKET_PREFS_KEY.key, AppConstants.BASCKET_JSON_KEY.key)
  }

  init {
    Log.d("TAG", "DataMandger is Created in companion object")
  }

  companion object {
    val create: DataManager by lazy { DataManager(App.applicationContext()) }
  }

  // Application mocks

  fun fetchMocks(): List<NoteBook> = mRepositoryMockNoteBookS.fetchMocks()
  fun fetchCarMocks(): List<CarPart> = mRepositoryMockCarParts.fetchMocks()
  fun fetchVidioCardMocks(): List<VideoCard> = mRepositoryMockVidoCard.fetchMocks()

  // Realm database functions

  fun setUser(user: UserRealm): Unit { //realmHelper.saveUserRealm(user)
    realmDbHelper.save(user)
  }

  fun getUser(): UserRealm? {
    return realmDbHelper.getElementById(UserRealm::class.java,
      AppConstants.REALM_USER_ID.intKey!!) //return realmHelper.getUser()
  }

  fun clearUser() { //realmHelper.clearUser()
    realmDbHelper.dropRealmTable(UserRealm::class.java)
  }

  // Cashe
  fun getCasheNotebook() = casheNoteBook.getList()

  fun saveCasheNoteBook(list: List<NoteBook>) {
    casheNoteBook.saveList(list)
    //realmDbHelper.saveAll(list)
  }

  fun getCasheCarPart() = casheCarPart.getList()
  fun saveCasheCarPart(list: List<CarPart>) = casheCarPart.saveList(list)

  fun getCasheVideoCard() = casheVideoCard.getList()
  fun saveCasheVideoCard(list: List<VideoCard>) = casheVideoCard.saveList(list)

  // Bascket
  fun addBascket(id: BascketItem) = bascketHelper.put(id)

  fun getFromBasket(): MutableSet<BascketItem> = bascketHelper.get()
  fun clearBascket() = bascketHelper.clearBascket()
  fun removeFromBascket(bascketItem: BascketItem?) {
    bascketHelper.removeFromBaskcet(bascketItem)
  }

  fun getChosenList(): MutableList<Int> {
    var chosenList = mutableListOf<Int>()
    val items = getFromBasket()
    for (item in items) chosenList.add(item.id)
    return chosenList
  }

  //  fun getChosenListF(): MutableList<Int>? {
  //
  //    return firebaseHelper.getChosenList()?.toMutableList()
  //  }

  // Firebase database fucntions

  fun saveToFirebase(userName: String) {
    firebaseHelper.saveUserName(userName = userName)
  }

  fun saveChosenListtoFirebase(chosenList: List<CloudBasketItem>) {
    firebaseHelper.saveChosenList(chosenList)
  }
}





