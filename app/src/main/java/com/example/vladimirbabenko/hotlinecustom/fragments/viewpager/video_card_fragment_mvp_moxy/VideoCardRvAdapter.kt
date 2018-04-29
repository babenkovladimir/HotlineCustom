package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.video_card_fragment_mvp_moxy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_video_card.view.btVideoCardStar
import kotlinx.android.synthetic.main.item_video_card.view.ivVideoCardImage
import kotlinx.android.synthetic.main.item_video_card.view.tvVideCardMemorySize
import kotlinx.android.synthetic.main.item_video_card.view.tvVideoCardName
import kotlinx.android.synthetic.main.item_video_card.view.tvVideoCardPrice

class VideoCardRvAdapter : RecyclerView.Adapter<VideoCardRvAdapter.VideoCardViewHolder>() {

  private var videoCardList = emptyList<VideoCard>()
  private var chosenList = emptyList<Int>()

  fun setData(videoCardList: List<VideoCard>, chosenList: List<Int>) {
    this.videoCardList = videoCardList
    this.chosenList = chosenList
    notifyDataSetChanged()
  }

  fun refreshChosenList(chosenList: List<Int>){
    this.chosenList = chosenList
    notifyDataSetChanged()
  }

  fun getElementAtPosition(position: Int): VideoCard = videoCardList[position]

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoCardViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_card, parent, false)
    return VideoCardViewHolder(view)
  }

  override fun getItemCount(): Int = videoCardList.count()

  override fun onBindViewHolder(holder: VideoCardViewHolder, position: Int) {
    holder.bind(videoCardList[position], chosenList.contains(videoCardList[position].id))
  }

  class VideoCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(videoCard: VideoCard, isInBascket: Boolean): Unit {
      with(itemView) {
        tvVideCardMemorySize.text = "RAM ${videoCard.memorySize}GB"
        tvVideoCardName.text = videoCard.name
        tvVideoCardPrice.text = "$ ${videoCard.price}"
        btVideoCardStar.isSelected = isInBascket

        Picasso.get()
          .load(videoCard.photoUrl)
          .fit()
          .placeholder(R.drawable.ic_launcher_foreground)
          .into(ivVideoCardImage)
      }
    }
  }
}