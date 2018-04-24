package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.notebook_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.event_bus.Events
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.NotebookMapper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.btCarPartCloseDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.btStarButton
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.etWikiLink
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.ivCarPartImageDialog
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPartDetailsDescription
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPartIdDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPriceDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvPartNameDetails

class NoteBookDetailsFragment() : DialogFragment() {

  val dataManager = DataManager.create
  val bus = GlobalBus.instance

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  bus.register(this)
    isCancelable = true
  }

  companion object {
    fun newInstance(bundle: Bundle?): NoteBookDetailsFragment {
      val fragment = NoteBookDetailsFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    val view = inflater.inflate(R.layout.fragment_car_part_dialog, container, false)

    val noteBook = arguments?.getParcelable(AppConstants.NOTEBOOK_PART_BUNDL.key) as? NoteBook

    view.tvPartNameDetails.text = noteBook?.model
    view.tvCarPartDetailsDescription.text = noteBook?.description
    view.tvCarPriceDetails.text = "$ " + noteBook?.price.toString()
    view.tvCarPartIdDetails.text = "id:${noteBook?.id.toString()}"
    view.btStarButton.isSelected = arguments!!.getBoolean("NotebookIsInBascket")

    Picasso
      .get()
      .load(noteBook?.photUrl)
      .fit()
      .placeholder(R.drawable.ic_launcher_background)
      .into(view.ivCarPartImageDialog)

    view.btStarButton.setOnClickListener() {
      if (!it.isSelected) {
        it.isSelected = true
        dataManager.addBascket(NotebookMapper().transform(noteBook!!))
        dataManager.prefs.modifyBascketSize(1)
        bus.post(Events.BascketEvent())
        bus.post(Events.NotebookFragmentRefresh())
      } else {
        it.isSelected = false
        dataManager.removeFromBascket(NotebookMapper().transform(noteBook!!))
        dataManager.prefs.modifyBascketSize(-1)
        bus.post(Events.BascketEvent())
        bus.post(Events.NotebookFragmentRefresh())
      }
    }

    view.etWikiLink.setOnClickListener({
      val intent = Intent()
      intent.action = Intent.ACTION_VIEW
      val uri = Uri.parse("https://en.wikipedia.org/wiki/" + noteBook?.brand)
      intent.data = uri
      context?.startActivity(intent)
    })
    view.btCarPartCloseDetails.setOnClickListener() {
      bus.post(Events.CarFragmentRefresh())
      dismiss()
    }
    return view
  }

  override fun onDestroy() {
    bus.unregister(this)
    super.onDestroy()
  }
}