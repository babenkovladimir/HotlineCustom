package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.btCarPartCloseDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.btStarButton
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.etWikiLink
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.ivCarPartImageDialog
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPartDetailsDescription
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPartIdDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPriceDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvPartNameDetails

class PartDetailsFragment() : DialogFragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isCancelable = true
  }

  companion object {
    fun newInstance(bundle: Bundle?): PartDetailsFragment {
      val fragment = PartDetailsFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    val view = inflater.inflate(R.layout.fragment_car_part_dialog, container, false)

    val carPart = arguments?.getParcelable(AppConstants.CAR_PART_BUNDLE.key) as? CarPart

    view.tvPartNameDetails.text = carPart?.name
    view.tvCarPartDetailsDescription.text = carPart?.description
    view.tvCarPriceDetails.text = "$ " + carPart?.partPrice.toString()
    view.tvCarPartIdDetails.text = "id:${carPart?.id.toString()}"

    view.btStarButton.setOnClickListener() {
      if (!it.isSelected) {it.isSelected = true} else it.isSelected = false
    }

    view.etWikiLink.setOnClickListener({
      val intent = Intent()
      intent.action = Intent.ACTION_VIEW
      val uri = Uri.parse("https://en.wikipedia.org/wiki/" + carPart?.name)
      intent.data = uri
      context?.startActivity(intent)
    })

    view.btCarPartCloseDetails.setOnClickListener() {
      //TODO( add id to bucket???)

      dismiss()
    }

    Picasso.get().load(carPart?.partPhotoUrl).fit().placeholder(R.drawable.ic_launcher_background)
      .into(view.ivCarPartImageDialog)

    return view
  }
}