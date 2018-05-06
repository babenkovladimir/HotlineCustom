package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_bascket.tvTotalPrice
import kotlinx.android.synthetic.main.item_basket_item.view.btCardViewMinus
import kotlinx.android.synthetic.main.item_basket_item.view.btCardViewPlus
import kotlinx.android.synthetic.main.item_basket_item.view.ivBascketImage
import kotlinx.android.synthetic.main.item_basket_item.view.tvIdBasketItem
import kotlinx.android.synthetic.main.item_basket_item.view.tvItemCount
import kotlinx.android.synthetic.main.item_basket_item.view.tvNameBasketItem
import kotlinx.android.synthetic.main.item_basket_item.view.tvPriceBasketItem
import java.lang.ref.WeakReference

class BascketRVadapter : RecyclerView.Adapter<BascketRVadapter.BascketWrapperViewHolder>() {

  private var basketList = emptySet<BascketItem>()
  lateinit var activity: WeakReference<BascketActivity>

  fun linck(activity: BascketActivity) {
    this.activity = WeakReference(activity)
  }

  fun unLink() {
    this.activity.clear()
  }

  fun setItems(items: Set<BascketItem>) {
    basketList = items
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BascketWrapperViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket_item, parent, false)
    val holder = BascketWrapperViewHolder(view)



    return holder
  }

  override fun getItemCount(): Int = basketList.size

  override fun onBindViewHolder(holder: BascketWrapperViewHolder, position: Int) {
    holder.bind(basketList.elementAt(position), position)
    holder.setIncrementListner(object: IncrementListner{
      override fun onIncrement(deltaValue: Int, position: Int) {
        activity.get()?.presenter?.update(deltaValue, position)
      }
    })
  }


  // View holder
  class BascketWrapperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var incrementListner: IncrementListner

    fun setIncrementListner(listner: IncrementListner): Unit {
      this.incrementListner = listner
    }

    fun bind(item: BascketItem, position: Int) {
      Log.d("TAGIL", item.toString())
      var itemsCount = item.num

      itemView.tvNameBasketItem.text = item.name
      itemView.tvPriceBasketItem.text = "$ ${item.price}"
      itemView.tvIdBasketItem.text = item.id.toString()
      itemView.tvItemCount.text = itemsCount.toString()

      itemView.btCardViewPlus.setOnClickListener() {
        //itemView.tvItemCount.text = "${++itemsCount}"
        incrementListner.onIncrement(+1, position)
      }

      itemView.btCardViewMinus.setOnClickListener() {
        //@Produce fun increase() = Events.PriceCorrectionEvent(1)
        if (itemsCount!! > 0) {
          //itemView.tvItemCount.text = "${--itemsCount}"
          incrementListner.onIncrement(-1, position)
        }
      }

      Picasso.get().load(item.photoUrl).fit().placeholder(R.drawable.ic_launcher_foreground)
        .into(itemView.ivBascketImage)
    }
  }
}