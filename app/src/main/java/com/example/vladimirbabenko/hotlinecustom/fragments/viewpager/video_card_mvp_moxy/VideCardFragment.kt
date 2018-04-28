package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.video_card_mvp_moxy

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager

class VideCardFragment() :Fragment(){//, IVideoCardView {

  /*
  * фadd presenter to our frsgmemnt
  * */
 // @InjectPresenter(type = PresenterType.GLOBAL) lateinit var presenter: VideoCardPresenter

  lateinit var dataManager: DataManager

  companion object {
    val title = "Some..."

    fun newInstance(bundle: Bundle?): VideCardFragment {
      val fragment = VideCardFragment()
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
    return inflater.inflate(R.layout.fragment_some, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    //view.btCallPresenter.setOnClickListener(){presenter.showMessagePresenter()}

  }

  // MVP implementation

//  override fun showmessage(message: String) {
//    tvVideoCardText.text = message
//
//  }
}