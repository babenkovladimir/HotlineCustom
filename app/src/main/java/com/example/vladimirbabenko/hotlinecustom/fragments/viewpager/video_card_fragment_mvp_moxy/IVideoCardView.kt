package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.video_card_fragment_mvp_moxy

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface IVideoCardView : MvpView {

  fun showList(listVideoCard: List<VideoCard>, chosenList: List<Int>)

  fun refreshChosenList(chosenList: List<Int>)

  fun singleClickShowDialogFragment(videoCard: VideoCard, isInBuscket: Boolean)
}