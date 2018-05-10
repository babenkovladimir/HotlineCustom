package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.event_bus.Events.CarFragmentRefresh
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.ItemClickSupport
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_car_parts.view.rvCarParts

class CarPartsFragment() : Fragment(), ICarPartsView {

  val presenter: CarPartsPresenter
  val bus = GlobalBus.instance
  var adapter: CarRVAdapter
  lateinit var recyclerView: RecyclerView
  //var layoutManager: LinearLayoutManager
  lateinit var itemDecoration: DividerItemDecoration

  init {
    presenter =
        com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp.CarPartsPresenter()
    //layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    adapter =
        com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp.CarRVAdapter()
  }

  companion object {
    val title: String = "Car parts"
      get

    fun newInstance(bundle: Bundle?): CarPartsFragment {
      val fragment = CarPartsFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter.bind(this)
    bus.register(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    val view: View = inflater.inflate(R.layout.fragment_car_parts, container, false)



    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    recyclerView = view.rvCarParts
    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    // RecyclerView item decoration
    //    itemDecoration = DividerItemDecoration(recyclerView.context, LinearLayout.VERTICAL)
    //    recyclerView.addItemDecoration(itemDecoration)
    recyclerView.adapter = adapter

    presenter.fetchMocks()

    ItemClickSupport.addTo(recyclerView)
      .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
        override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {

          YoYo.with(Techniques.ZoomIn)
            .onEnd({ presenter.onItemClicked(position) }) // Обращение к преентеру показать
            .duration(200).playOn(v)
        }
      }).setOnItemLongClickListener(object : ItemClickSupport.OnItemLongClickListener {
        override fun onItemLongClicked(recyclerView: RecyclerView?, position: Int,
          v: View?): Boolean {

          val builder = AlertDialog.Builder(view.context)
          builder.setTitle("Spear part").setMessage("You should by it!!!").setCancelable(true)

          val dialog = builder.create()
          dialog.show()
          return true
        }
      })

    //TODO fil the fragment with logic and add some listner or some other feature


  }

  @Subscribe fun getFromDialog(refresh: CarFragmentRefresh) {
    Toast.makeText(context, "Catch", Toast.LENGTH_LONG).show()
    presenter.fetchMocks()
  }

  override fun onSaveInstanceState(outState: Bundle) { // outState.putInt("position", )
    super.onSaveInstanceState(outState)
  }

  override fun onDestroy() {
    presenter.unbind()
    bus.unregister(this)
    super.onDestroy()
  }

  // MVP implementation

  override fun showPartList(partList: List<CarPart>, chosenList: MutableList<Int>) {
    adapter.setCarParts(partList, chosenList)
  }

  override fun handleSingleClick(position: Int?, carPart: CarPart, isInBascket: Boolean) {
    val bundle = Bundle()
    bundle.putParcelable(AppConstants.CAR_PART_BUNDLE.key, carPart)
    bundle.putBoolean(AppConstants.IS_IN_BASKET.key, isInBascket)

    val partDetailsFragment = PartDetailsFragment.newInstance(bundle)
    val manager = childFragmentManager
    partDetailsFragment.show(manager, "PartDetails")
  }
}