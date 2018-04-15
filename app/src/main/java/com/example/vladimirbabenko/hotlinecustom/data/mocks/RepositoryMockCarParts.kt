package com.example.vladimirbabenko.hotlinecustom.data.mocks

import com.example.vladimirbabenko.hotlinecustom.entity.CarPart

class RepositoryMockCarParts : IRepositoryMock<CarPart> {

  val id = Array(10, { yo -> 123456 * yo })
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
    "Lorem ipsum dolor sit amet, ad delenit epicurei pri. Qui et delenit appareat efficiantur. Mel accusamus accommodare ei, vix atqui necessitatibus ne, mel an ipsum assum suavitate. Cibo solet honestatis per et, pro an quodsi recteque. Nominati inimicus gubergren usu in, te eos tale veri. Hinc utroque has et, te nam probo diceret.\n" + "\n" + "Vim debet nihil inimicus ea, nec et altera ponderum conclusionemque. Vis harum graecis deterruisset an. Eos dicant vivendo copiosae ei, abhorreant instructior per ne. Sit in diam aeterno, te his nonumes legendos consectetuer.\n" + "\n" + "Tantas aliquip consulatu nec et. Quando sanctus hendrerit ea vix. Has ea petentium cotidieque, facete aliquip hendrerit cu cum. Usu cu eros ludus, habeo probatus lobortis est at, malis ullum similique duo et. Veniam periculis te mea, duis partiendo maiestatis pri an. Ex quando vocibus dignissim vim, pro brute movet an, ius facer fierent ei."

  init {

  }

  override fun fetchMocks(): List<CarPart> {
    val partList = ArrayList<CarPart>()

    for (i in id) {
      partList.add(CarPart(id[i], partName[i], partPrice[i], partPhotoUrl[i], description))
    }
    return partList
  }
}