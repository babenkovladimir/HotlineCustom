package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.video_card_fragment_mvp_moxy

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.VideoCard
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.ItemClickSupport
import kotlinx.android.synthetic.main.fragment_video_card.view.rvVideoCard

class VideCardFragment() : MvpAppCompatFragment(), IVideoCardView {

  /*
  * add presenter to our fragmemnt
  */
  @InjectPresenter(type = PresenterType.LOCAL) lateinit var presenter: VideoCardPresenter

  lateinit var dataManager: DataManager
  lateinit var adapter: VideoCardRvAdapter
  lateinit var recyclerViev: RecyclerView
  lateinit var layoutManager: LinearLayoutManager

  init {
    adapter = VideoCardRvAdapter()
  }

  companion object {
    val title = "VideoCards"

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
    val view = inflater.inflate(R.layout.fragment_video_card, container, false)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    recyclerViev = view.rvVideoCard
    layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    recyclerViev.layoutManager = layoutManager
    recyclerViev.adapter = adapter

    ItemClickSupport.addTo(recyclerViev)
      .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
        override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
          YoYo.with(Techniques.ZoomIn)
            .onEnd({ presenter.handleSingleClick(position) }) // Обращение к преентеру показать
            .duration(200).playOn(v)
        }
      })
  }

  // MVP implementation

  override fun showList(listVideoCard: List<VideoCard>, chosenList: List<Int>) {
    adapter.setData(listVideoCard, chosenList)
  }

  override fun singleClickShowDialogFragment(videoCard: VideoCard, isInBuscket: Boolean) {
    val bundle = Bundle()
    bundle.putParcelable(AppConstants.VIDEO_CARD_BUNDLE.key, videoCard)
    bundle.putBoolean(AppConstants.IS_IN_BASKET.key, isInBuscket)
    val fragment = VideoCardDetailsFragment.newInstance(bundle)
    fragment.show(childFragmentManager, "VideoCardDetailsFragment")
  }

  /*
  * Fun gets refreshed chosen list and put it into adapter
  * */
  override fun refreshChosenList(chosenList: List<Int>) {
    adapter.refreshChosenList(chosenList)
  }
}