package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.video_card_fragment_mvp_moxy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard
import com.example.vladimirbabenko.hotlinecustom.event_bus.Events
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.mappers.VideoCardMapper
import com.squareup.otto.Bus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.btCarPartCloseDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.btStarButton
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.etWikiLink
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.ivCarPartImageDialog
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPartDetailsDescription
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPartIdDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvCarPriceDetails
import kotlinx.android.synthetic.main.fragment_car_part_dialog.view.tvPartNameDetails

class VideoCardDetailsFragment : DialogFragment() {

  lateinit var bus: Bus
  val dataManager = DataManager.create

  companion object {
    fun newInstance(bundle: Bundle?): VideoCardDetailsFragment {
      val fragment = VideoCardDetailsFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    bus = GlobalBus.instance
    bus.register(this)
    isCancelable = true
    return inflater.inflate(R.layout.fragment_car_part_dialog, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val videoCard = arguments?.getParcelable(AppConstants.VIDEO_CARD_BUNDLE.key) as VideoCard
    val isSelected = arguments?.getBoolean(AppConstants.IS_IN_BASKET.key, false)

    with(videoCard) {
      with(view) {
        tvPartNameDetails.text = name
        tvCarPriceDetails.text = "S " + price
        tvCarPartIdDetails.text = "id: " + videoCard.id
        tvCarPartDetailsDescription.text = description
        btStarButton.isSelected = isSelected!!

        Picasso.get().load(videoCard.photoUrl).fit().placeholder(R.drawable.ic_launcher_background)
          .into(ivCarPartImageDialog)
      }
    }

    view.btStarButton.setOnClickListener() {
      if (!it.isSelected) {
        it.isSelected = true
        dataManager.addBascket(
          VideoCardMapper().transform(videoCard))
        dataManager.prefs.modifyBascketSize(1)
        bus.post(Events.BascketEvent())
      } else {
        it.isSelected = false
        dataManager.removeFromBascket(
          VideoCardMapper().transform(videoCard))
        dataManager.prefs.modifyBascketSize(-1)
        bus.post(Events.BascketEvent())
      }
    }

    view.etWikiLink.setOnClickListener({
      val intent = Intent()
      intent.action = Intent.ACTION_VIEW
      val uri = Uri.parse("https://en.wikipedia.org/wiki/" + videoCard.name)
      intent.data = uri
      context?.startActivity(intent)
    })

    view.btCarPartCloseDetails.setOnClickListener() {
      bus.post(Events.VideoCardRefreshFragment())
      dismiss()
    }
  }

  override fun onDestroy() {
    bus.unregister(this)
    super.onDestroy()
  }
}