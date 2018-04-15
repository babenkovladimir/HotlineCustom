package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

class CarPartsFragment() : Fragment(), ICarPartsView {

  lateinit var dataManager: DataManager

  companion object {
    val title: String = "Car parts"
      get

    fun newInstance(bundle: Bundle?): CarPartsFragment {
      val fragment =
        CarPartsFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataManager = DataManager.create
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    val view: View = inflater.inflate(R.layout.fragment_car_parts, container, false)


    //TODO fil the fragment with logic

    return view
  }
}