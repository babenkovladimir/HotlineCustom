package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.video_card_mvp_moxy

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class VideoCardPresenter :  MvpPresenter<IVideoCardView>() { //class VideoCardPresenter : MvpPresenter() {

  /*
  * Load all info foer example
  * */
  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
  }

  fun showMessagePresenter() {
    viewState.showmessage("Message from Presenter")
  }
}