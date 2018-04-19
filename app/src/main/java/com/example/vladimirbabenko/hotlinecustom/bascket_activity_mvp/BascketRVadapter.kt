package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem

class BascketRVadapter:RecyclerView.Adapter<BascketRVadapter.BascketWrapperViewHolder>() {

  private var basketList = emptySet<BascketItem>()

  fun setItems(items: Set<BascketItem>){
    basketList = items
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BascketWrapperViewHolder {
    TODO(
      "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getItemCount(): Int = basketList.size

  override fun onBindViewHolder(holder: BascketWrapperViewHolder, position: Int) {
    holder.bind(basketList.elementAt(position))
  }











  class BascketWrapperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(item: BascketItem){
      // Create Binding


    }


  }
}