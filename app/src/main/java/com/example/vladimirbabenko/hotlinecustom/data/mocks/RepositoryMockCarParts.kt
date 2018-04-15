package com.example.vladimirbabenko.hotlinecustom.data.mocks

import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook

class RepositoryMockCarParts : IRepositoryMock<CarPart> {

  lateinit var partList: ArrayList<CarPart>

  val id = Array(10, { yo -> 123456 * yo +1248754})
  val partName = listOf<String>("Rings", "Piston", "Gasket", "rocker", "Valve", "Oil", "Air filter",
    "Accumulyator", "Tyre", "Disc")
  val partPrice = listOf(120, 300, 200, 80, 50, 12, 5, 80, 70, 75)
  val partPhotoUrl =
    listOf<String>("http://auto-wiki.ru/uploads/08.2015/neispravnost-porshnevyh-kolec.jpg",
      "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Piston_2.jpg/1200px-Piston_2.jpg",
      "http://auto-wiki.ru/uploads/09.2014/prokladki-gbc.jpg",
      "https://wiki.zr.ru/images/thumb/d/df/%D0%A8%D0%B0%D1%82%D1%83%D0%BD_24.jpg/350px-%D0%A8%D0%B0%D1%82%D1%83%D0%BD_24.jpg",
      "https://wiki.zr.ru/images/thumb/6/6a/%D0%9A%D0%BB%D0%B0%D0%BF%D0%B0%D0%BD%D1%8B_28.jpg/250px-%D0%9A%D0%BB%D0%B0%D0%BF%D0%B0%D0%BD%D1%8B_28.jpg",
      "http://auto-wiki.ru/uploads/01-2016/klassifikaciya-motornyh-masel.jpg",
      "https://wiki.zr.ru/images/thumb/7/73/%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9_%D0%B2%D0%BF%D1%80%D1%8B%D1%81%D0%BA_71-3.jpg/300px-%D0%A0%D0%B0%D1%81%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9_%D0%B2%D0%BF%D1%80%D1%8B%D1%81%D0%BA_71-3.jpg",
      "http://auto-wiki.ru/uploads/08.2015/tehnika-bezopasnosti-pri-rabote-s-avtomobilnym-akkumulyatorom.jpg",
      "https://wiki.zr.ru/images/thumb/c/c1/%D0%9A%D0%BE%D0%BB%D0%B5%D1%81%D0%B0_%D0%BF%D0%BE%D0%B4%D0%B2%D0%B5%D1%81%D0%BA%D0%B0_%D0%BC%D0%BE%D1%81%D1%82%D1%8B_6.jpg/500px-%D0%9A%D0%BE%D0%BB%D0%B5%D1%81%D0%B0_%D0%BF%D0%BE%D0%B4%D0%B2%D0%B5%D1%81%D0%BA%D0%B0_%D0%BC%D0%BE%D1%81%D1%82%D1%8B_6.jpg",
      "https://upload.wikimedia.org/wikipedia/commons/3/34/KFZ1560.jpg")
  val description =
    "Lorem ipsum dolor sit amet, ad delenit epicurei pri. Qui et delenit appareat efficiantur. Mel accusamus accommodare ei, vix atqui necessitatibus ne, mel an ipsum assum suavitate. Cibo solet honestatis per et, pro an quodsi recteque. Nominati inimicus gubergren usu in, te eos tale veri..."

  init {

  }

  override fun fetchMocks(): List<CarPart> {
    partList = ArrayList()


    //for (i in id) { throw arrayOutOfBounException
    for (i in 0..9) {
      partList.add(CarPart(id[i], partName[i], partPrice[i], partPhotoUrl[i], description))
    }
    return partList
  }


}