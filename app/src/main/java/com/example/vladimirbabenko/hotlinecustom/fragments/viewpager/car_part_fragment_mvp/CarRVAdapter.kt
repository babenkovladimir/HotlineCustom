package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_car_part.view.btCarPartStar
import kotlinx.android.synthetic.main.item_car_part.view.ivCarPartImage
import kotlinx.android.synthetic.main.item_car_part.view.tvCarParId
import kotlinx.android.synthetic.main.item_car_part.view.tvCarPartName
import kotlinx.android.synthetic.main.item_car_part.view.tvCarPartPrice

class CarRVAdapter : RecyclerView.Adapter<CarRVAdapter.CarViewHolder>() {
  
  private var partList: List<CarPart> = emptyList() // Use delegateObservable
  private val dataManager = DataManager.create

  fun setCarParts(partList: List<CarPart>){
    this.partList = partList
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
    val v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_car_part, parent, false)
    return CarViewHolder(v)
  }

  override fun getItemCount(): Int = partList.size

  override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
    //TODO is in Bascket
    var isInBasket = true
    holder.bind(partList[position], isInBasket )
  }

  class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(carPart: CarPart, isInBasket: Boolean){

      Picasso.get()
        .load(carPart.partPhotoUrl)
        .fit()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(itemView.ivCarPartImage)

      itemView.tvCarParId.text=carPart.id.toString()
      itemView.tvCarPartName.text = carPart.name
      itemView.tvCarPartPrice.text = "price: ${carPart.partPrice}"
      itemView.btCarPartStar.isSelected = isInBasket

    }

  }
}
