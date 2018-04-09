package com.example.vladimirbabenko.hotlinecustom.data

import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import java.util.Random

class RepositoryMockNoteBookS : IRepositoryMock<NoteBook> {
  lateinit var noteBookList: ArrayList<NoteBook>

  val brands: Set<String> = setOf("Apple", "Dell", "HP", "Asus", "Lenovo", "Xiaomi")
  val models = listOf<String>("Model 1", "Model 2", "Model 3", "Model 4", "Model 5")

  // Map of processor and its price
  val processors: Map<String, Int> =
    mapOf(Pair("Core i3", 100), Pair("Core i5", 200), Pair("Core i7", 300), Pair("Ryzen 3", 50))

  val videoCards: Map<String, Int> =
    mapOf(Pair("GeForce 1050", 220), Pair("GeForce 1050 Ti", 270), Pair("GeForce 1060", 330),
      Pair("RX470", 180), Pair("RX580", 215))

  // only for testing Mutable Collections
  val hdds: MutableMap<String, Int> =
    mutableMapOf(Pair("Seagate 1000GB", 70), Pair("WD Green 500", 30), Pair("Samsung 750 EVO", 100),
      Pair("Samsung 960 EVO", 200))

  val photosUrls =
    listOf<String>("http://www.imaster.od.ua/sites/default/files/noyt_ne_zaryagaetsya.jpg",
      "https://www.google.com/search?q=%D0%BD%D0%BE%D1%83%D1%82%D0%B1%D1%83%D0%BA+%D1%84%D0%BE%D1%82%D0%BE&client=firefox-b-ab&source=lnms&tbm=isch&sa=X&ved=0ahUKEwi4hrKwuKraAhUoDJoKHQdND6gQ_AUICigB&biw=1344&bih=705#imgrc=CNO4C4VY_3zL_M:",
      "https://olegvoloshchuk.com/wp-content/uploads/2012/10/notebook.png",
      "https://files.adme.ru/files/news/part_158/1581065/3898215-yvq0v-1505995095-650-47bc419964-1506431291.jpg",
      "https://img.aleco.com.ua/products/_original/p82583_15084902_noutbuk_dell_inspiron_3552_i35c45dil_6b.jpg",
      "https://itc.ua/img/ko/2004/51/HP_nw8000.jpg",
      "https://itc.ua/wp-content/uploads/2015/04/acer-671x362.jpg")

  override fun fetchMocks(): List<NoteBook> {

    noteBookList = ArrayList()

    for (i in 1..15) {
      var brand = brands.elementAt(Random().nextInt(brands.size))
      var model = models.elementAt(Random().nextInt(models.size))
      var processor = processors.entries.elementAt(Random().nextInt(processors.size))
      var videoCard = videoCards.entries.elementAt(Random().nextInt(processors.size))
      var hdd = hdds.entries.elementAt(Random().nextInt(hdds.size))
      var photoUrl = photosUrls.elementAt(Random().nextInt(photosUrls.size))

      var price = processor.value + videoCard.value + hdd.value


      noteBookList.add(
        NoteBook(brand, model, price, processor.key, videoCard.key, hdd.key, photoUrl))
    }

    return noteBookList
  }
}