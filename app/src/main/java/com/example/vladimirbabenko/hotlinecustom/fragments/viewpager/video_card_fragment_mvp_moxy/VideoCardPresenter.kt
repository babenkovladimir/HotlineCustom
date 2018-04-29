package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.video_card_fragment_mvp_moxy

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard
import com.example.vladimirbabenko.hotlinecustom.event_bus.Events
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.squareup.otto.Subscribe

@InjectViewState class VideoCardPresenter :
  MvpPresenter<IVideoCardView>() { //class VideoCardPresenter : MvpPresenter() {

  private val dataManager = DataManager.create
  private val bus = GlobalBus.instance
  private var videoCardList = emptyList<VideoCard>()

  init {
    bus.register(this)
  }

  /*
    * Load all info foer example
    */
  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    if (dataManager.prefs.withInternetConnection) {
      dataManager.saveCasheVideoCard(dataManager.fetchVidioCardMocks())
    }
    videoCardList = dataManager.getCasheVideoCard()
    viewState.showList(videoCardList, getChosenList())
  }

  @Subscribe fun refreshAdapter(event: Events.VideoCardRefreshFragment) {
    val chosenList = dataManager.getChosenList()
    viewState.refreshChosenList(chosenList)
  }

  override fun onDestroy() {
    bus.unregister(this)
    super.onDestroy()
  }

  /*
  * The function handles event onClick on recyclervView element
  * */
  fun handleSingleClick(position: Int) {
    viewState.singleClickShowDialogFragment(videoCardList[position], getChosenList().contains(videoCardList[position].id))
  }

  // Private helpers

  private fun getChosenList(): List<Int> {
    return dataManager.getChosenList()
  }
}