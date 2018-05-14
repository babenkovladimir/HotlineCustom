package com.example.vladimirbabenko.hotlinecustom.data

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import com.example.vladimirbabenko.hotlinecustom.event_bus.Events
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASHE_CAR_PART_PREFS_KEY
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASHE_NOTEBOOK_PREF_KEY
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASHE_VIDEO_CARD_PREFS_KEY
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASH_CAR_PART_JSON_KEY
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASH_NOTEBOOK_JSON_KEY
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants.CASH_VIDEO_CARD_JSON_KEY
import com.example.vladimirbabenko.hotlinecustom.utils.mappers.MapperBasketItemToCloudBasketItem
import com.example.vladimirbabenko.hotlinecustom.utils.mappers.MapperCloudBascketItemToBAsketItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED

class DataManager private constructor(context: Context) {

  val prefs: PreferencesHelper =
    com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper(context)
  private val mRepositoryMockNoteBookS = RepositoryMockNoteBookS()
  private val mRepositoryMockCarParts = RepositoryMockCarParts()
  private val mRepositoryMockVidoCard = RepositoryMockVidoCard()
  val bus = GlobalBus.instance

  // Realm Helper class - мой хелпер класс
  private val realmHelper = com.example.vladimirbabenko.hotlinecustom.data.RealmHelper()

  // Realm DbHelper from Nikita

  private val realmDbHelper: RealmDbHelper = RealmDbHelper()

  // FirebaseDB

  private val firebaseHelper: FirebaseDBHelper by lazy {
    com.example.vladimirbabenko.hotlinecustom.data.FirebaseDBHelper(getUser()!!.userId)
  }

  private val casheNoteBook: CasheNotebookJ =
    CasheNotebookJ(context, CASHE_NOTEBOOK_PREF_KEY.key, CASH_NOTEBOOK_JSON_KEY.key)

  private val casheCarPart: CasheCarPart by lazy(SYNCHRONIZED) {
    CasheCarPart(context, CASHE_CAR_PART_PREFS_KEY.key, CASH_CAR_PART_JSON_KEY.key)
  }

  private val casheVideoCard: CasheVideoCard =
    CasheVideoCard(context = context, PREFS_KEY = CASHE_VIDEO_CARD_PREFS_KEY.key,
      jsonKey = CASH_VIDEO_CARD_JSON_KEY.key)

  private val bascketHelper: BascketHelper by lazy(SYNCHRONIZED) {
    com.example.vladimirbabenko.hotlinecustom.data.BascketHelper(context, AppConstants.BASCKET_PREFS_KEY.key,
      AppConstants.BASCKET_JSON_KEY.key)
  }

  init {
    Log.d("TAG", "DataMandger is Created in companion object")
    bus.register(this)
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

  // Cashe by Realm
  fun getCasheNotebook(): List<NoteBook> { //return casheNoteBook.getList()
    return realmDbHelper.getAll(NoteBook::class.java)
  }

  fun saveCasheNoteBook(list: List<NoteBook>) {
    casheNoteBook.saveList(list)
    realmDbHelper.saveAll(list)
  }

  fun saveCasheCarPart(list: List<CarPart>): Unit {
    casheCarPart.saveList(list)
    realmDbHelper.saveAll(list)
  }

  fun getCasheCarPart(): List<CarPart> { //return casheCarPart.getList()
    return realmDbHelper.getAll(CarPart::class.java)
  }

  fun saveCasheVideoCard(list: List<VideoCard>): Unit {
    casheVideoCard.saveList(list)
    realmDbHelper.saveAll(list)
  }

  fun getCasheVideoCard(): List<VideoCard> { //casheVideoCard.getList()
    return realmDbHelper.getAll(VideoCard::class.java)
  }

  // Bascket
  fun addBascket(item: BascketItem): Unit {
    bascketHelper.put(item) // Not using now
    realmDbHelper.save(item)
  }

  fun getFromBasket(): MutableSet<BascketItem> { //return bascketHelper.get()
    return realmDbHelper.getAll(BascketItem::class.java).toMutableSet()
  }

  fun clearBascket(): Unit {
    bascketHelper.clearBascket()
    realmDbHelper.dropRealmTable(BascketItem::class.java)
  }

  fun removeFromBascket(bascketItem: BascketItem) {
    bascketHelper.removeFromBaskcet(bascketItem)
    realmDbHelper.deleteElementById(BascketItem::class.java, bascketItem.id!!)
  }

  /*
  * This void updates value "num" - quantity items
  * */
  fun updateBasketItemCountValue(basketItemToUpdate: BascketItem, value: Int) {
    val id = basketItemToUpdate.id!!
    realmDbHelper.updateValueNum<BascketItem>(BascketItem::class.java, id, value)
  }

  fun getChosenList(): MutableList<Int> {
    var chosenList = mutableListOf<Int>()
    val items = getFromBasket()
    for (item in items) chosenList.add(item.id!!)
    return chosenList
  }

  // Firebase

  fun saveToFirebaseUserName() {
    firebaseHelper.saveUserName(userName = getUser()!!.displayedName.toString())
  }

  fun saveBasketItemsToFirebase() {
    val basketItems = getFromBasket()
    val listCloudItems = mutableListOf<CloudBasketItem>()
    basketItems.forEach { item ->
      listCloudItems.add(MapperBasketItemToCloudBasketItem().transform(item))
    }
    firebaseHelper.saveBasketItemsToCloud(listCloudItems)
  }

  fun synhronizeVithCloud() { //val listBasketItems = mutableListOf<BascketItem>()
    // val cloudList = firebaseHelper.getListCloudBascketItems()
    //Log.d("TAGSYNCH", cloudList.toString())
    val ref = FirebaseDatabase.getInstance().reference

    ref.child("users".toString()).child(getUser()!!.userId).child("chosenList".toString())
      .addValueEventListener(object : ValueEventListener {
        override fun onDataChange(
          dataSnapshot: DataSnapshot?) { // It fails if use List<> instead ArrayList<>
          val typeToken = object : GenericTypeIndicator<ArrayList<CloudBasketItem>>() {}

          val list = dataSnapshot?.getValue(typeToken)
          val listBasketItems = mutableListOf<BascketItem>()
          list?.forEach { item ->
            listBasketItems.add(
              MapperCloudBascketItemToBAsketItem().transform(item)) // This is kostul
            realmDbHelper.saveAll<BascketItem>(listBasketItems)


//            Toast.makeText(App.applicationContext(), "Synchronizing is finished",
//              Toast.LENGTH_SHORT).show() // posts the event to invalidate options menu
            bus.post(Events.BascketEvent())
          }
        }

        override fun onCancelled(p0: DatabaseError?) {
        }
      })
  }
}







