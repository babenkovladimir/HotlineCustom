package com.example.vladimirbabenko.hotlinecustom.data.mocks

import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import java.util.Random

class RepositoryMockNoteBookS : IRepositoryMock<NoteBook> {
  lateinit var noteBookList: ArrayList<NoteBook>

  val ids = Array(16,{ jorik -> 12478+125*jorik})

  val brands: List<String> =
    listOf("Apple", "Dell", "HP", "Asus",
          "Lenovo", "Xiaomi", "Apple", "Dell",
          "HP", "Asus", "Lenovo", "Xiaomi",
          "AlienWare", "MiNoteBookPro", "Acer", "Levono")
  val models = listOf<String>(
      "Model 1", "Model 2", "Model 3", "Model 4",
      "Model 5", "Model 6", "Model 7", "Model 8",
      "Model 9", "Model 10", "Model 11", "Model 12",
      "Model 13", "Model 14", "Model 15", "Model 16")

  // Map of processor and its price
  val processors: Map<String, Int> =
    mapOf(
      Pair("Core i3", 110), Pair("Core i5", 210), Pair("Core i7", 310), Pair("Ryzen 3", 51),
      Pair("Core i3", 120), Pair("Core i5", 220), Pair("Core i7", 320), Pair("Ryzen 3", 53),
      Pair("Core i3", 130), Pair("Core i5", 230), Pair("Core i7", 330), Pair("Ryzen 3", 54),
      Pair("Core i3", 140), Pair("Core i5", 240), Pair("Core i7", 340), Pair("Ryzen 3", 55))
  val videoCards: Map<String, Int> =mapOf(
    Pair("GeForce 1050", 220), Pair("GeForce 1050 Ti", 200), Pair("GeForce 1060", 530), Pair("RX470", 480),
    Pair("RX580", 235),Pair("GeForce 1050", 250), Pair("GeForce 1050 Ti", 270),Pair("GeForce 1060", 330),
    Pair("RX580", 245),Pair("GeForce 1050", 290), Pair("GeForce 1050 Ti", 270),Pair("GeForce 1060", 330),
    Pair("RX580", 265),Pair("GeForce 1050", 280), Pair("GeForce 1050 Ti", 270),Pair("GeForce 1060", 330))

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
  val description = "Lorem ipsum dolor sit amet, ad delenit epicurei pri. Qui et delenit appareat efficiantur. Mel accusamus accommodare ei, vix atqui necessitatibus ne, mel an ipsum assum suavitate. Cibo solet honestatis per et, pro an quodsi recteque. Nominati inimicus gubergren usu in, te eos tale veri..."

  override fun fetchMocks(): List<NoteBook> {

    noteBookList = ArrayList()

    for (i in 0..15) {
      var id = ids.elementAt(i)
      var brand = brands.elementAt(i)
      var model = models.elementAt(i)
      var processor = processors.entries.elementAt(Random().nextInt(processors.size))
      var videoCard = videoCards.entries.elementAt(Random().nextInt(processors.size))
      var hdd = hdds.entries.elementAt(Random().nextInt(hdds.size))
      var photoUrl = photosUrls.elementAt(Random().nextInt(photosUrls.size))

      var price = processor.value + videoCard.value + hdd.value


      noteBookList.add(
        NoteBook(id, brand, model, price, processor.key, videoCard.key, hdd.key, photoUrl, description))
    }

    return noteBookList
  }
}