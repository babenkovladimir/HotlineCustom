package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_car_part.view.btCarPartStar
import kotlinx.android.synthetic.main.item_car_part.view.ivCarPartImage
import kotlinx.android.synthetic.main.item_car_part.view.tvCarParId
import kotlinx.android.synthetic.main.item_car_part.view.tvCarPartName
import kotlinx.android.synthetic.main.item_car_part.view.tvCarPartPrice

class CarRVAdapter : RecyclerView.Adapter<CarRVAdapter.CarViewHolder>() {

  private var partList: List<CarPart> = emptyList() // Use delegateObservable
  var bascketSet: Set<BascketItem> = emptySet()
  var unikList: MutableList<Int> = mutableListOf()
  lateinit var chosenList: List<Int>

  fun setCarParts(partList: List<CarPart>, chosenList: List<Int>) {
    this.partList = partList
    this.chosenList = chosenList

    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
    val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_car_part, parent, false)
    return CarViewHolder(v)
  }

  override fun getItemCount(): Int = partList.size

  override fun onBindViewHolder(holder: CarViewHolder, position: Int) { //Костыль!!!
    holder.bind(partList[position], chosenList.contains(partList[position].id))
  }

  // Card View Holder
  class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(carPart: CarPart, isInBasket: Boolean) {

      with(itemView) {
        Picasso.get().load(carPart.partPhotoUrl).fit()
          .placeholder(R.drawable.ic_launcher_foreground).into(ivCarPartImage)

        tvCarParId.text = carPart.id.toString()
        tvCarPartName.text = carPart.name
        tvCarPartPrice.text = "price: ${carPart.partPrice}"
        btCarPartStar.isSelected = isInBasket
      }
    }
  }
}
