package com.example.vladimirbabenko.hotlinecustom.data.mocks

import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard

class RepositoryMockVidoCard : IRepositoryMock<VideoCard> {

  val id = Array<Int>(10, { count -> (3.1415926535897 * count) as Int })
  val name =
    listOf<String>("GTX 750i", "GF1050Ti", "GF1070Ti", "Rx560", "Rx580", "Quatro", "GF1180",
      "GeForce MX440", "Voodoo", "Radeon 196Pro")
  val memorySize = listOf<Int>(2, 4, 3, 1, 4, 6, 8, 2, 3, 6)
  val price = listOf<Int>(100, 150, 250, 315, 874, 415, 1099, 200, 614, 497)
  val photoUrl =
    listOf<String>("https://3dnews.ru/assets/external/illustrations/2016/07/08/935906/726-1.jpg",
      "https://fthmb.tqn.com/nEOSyuKlb4h8ph3QpaPZOdk544M=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/xfx-amd-radeon-hd-5450-video-card-57c764225f9b5829f4b1d609.jpg",
      "http://www.nvidia.ru/docs/IO/145490/geforce-gtx-titan-z-product-shot.png",
      "https://cdn-st2.rtr-vesti.ru/vh/pictures/hdr/133/460/1.jpg",
      "https://p.fast.ulmart.ru/p/mid/92/9209/920968.jpg",
      "https://datbaze.ru/wp-content/uploads/2014/01/videokarta-integrirovannaya-v-materinskuyu-platu.jpg",
      "https://www.overclockers.ua/news/video/116526-pwc-r9-390-x2-2.jpg",
      "http://sidex.ru/images_offers/7/7027/videokarta_palit_geforce_gtx_660_ne5x660010491060f_980mhz_pcie_30_2048mb_6008mhz_192_bit_2x_3.jpg",
      "https://cdn.igromania.ru/mnt/news/0/b/e/e/2/5/67026/f9f9f425ccb3d8dc_848x477.jpg",
      "http://komputercnulja.ru/wp-content/uploads/2012/08/%D0%B2%D0%B8%D0%B4%D0%B5%D0%BE%D0%BA%D0%B0%D1%80%D1%82%D0%B0-2.jpg")

  val description: String =
    "Lorem ipsum dolor sit amet, ad delenit epicurei pri. Qui et delenit appareat efficiantur. Mel accusamus accommodare ei, vix atqui necessitatibus ne, mel an ipsum assum suavitate. Cibo solet honestatis per et, pro an quodsi recteque. Nominati inimicus gubergren usu in, te eos tale veri..."

  override fun fetchMocks(): List<VideoCard> {
    val list = mutableListOf<VideoCard>()

    for (i in 0..9) {
      list.add(
        VideoCard(id = id[i], name = name[i], photoUrl = photoUrl[i], memorySize = memorySize[i],
          price = price[i], description = description))
    }
    return list
  }
}