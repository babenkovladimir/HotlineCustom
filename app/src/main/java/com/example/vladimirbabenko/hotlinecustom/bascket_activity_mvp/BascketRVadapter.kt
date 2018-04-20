package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_basket_item.view.ivBascketImage
import kotlinx.android.synthetic.main.item_basket_item.view.tvNameBasketItem
import kotlinx.android.synthetic.main.item_basket_item.view.tvPriceBasketItem

class BascketRVadapter : RecyclerView.Adapter<BascketRVadapter.BascketWrapperViewHolder>() {

  private var basketList = emptySet<BascketItem>()

  fun setItems(items: Set<BascketItem>) {
    basketList = items
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BascketWrapperViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket_item, parent, false)

    return BascketWrapperViewHolder(view)
  }

  override fun getItemCount(): Int = basketList.size

  override fun onBindViewHolder(holder: BascketWrapperViewHolder, position: Int) {
    holder.bind(basketList.elementAt(position))
  }


  class BascketWrapperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: BascketItem) {
      itemView.tvNameBasketItem.text = item.name
      itemView.tvPriceBasketItem.text = "$ ${item.price}"

      Picasso.get()
        .load(item.photoUrl)
        .fit()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(itemView.ivBascketImage)
    }
  }
}