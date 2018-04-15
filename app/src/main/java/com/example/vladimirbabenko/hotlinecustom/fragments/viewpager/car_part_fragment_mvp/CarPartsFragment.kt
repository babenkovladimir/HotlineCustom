package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.data.DataManager.Companion
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.ItemClickSupport
import kotlinx.android.synthetic.main.fragment_car_parts.view.rvCarParts

class CarPartsFragment() : Fragment(), ICarPartsView {

  lateinit var dataManager: DataManager
  val presenter: CarPartsPresenter
  lateinit var adapter: CarRVAdapter
  lateinit var recyclerView: RecyclerView
  lateinit var layoutManager: LinearLayoutManager
  lateinit var itemDecoration: DividerItemDecoration

  init {
    Log.d("TAG", "CarPartsFragment() initialization and get instance of presenter")
    dataManager = DataManager.create
    presenter = CarPartsPresenter()
    layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    adapter = CarRVAdapter()
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
    dataManager = DataManager.create
    presenter.bind(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    val view: View = inflater.inflate(R.layout.fragment_car_parts, container, false)

    recyclerView = view.rvCarParts
    recyclerView.layoutManager = layoutManager
    itemDecoration = DividerItemDecoration(recyclerView.context, LinearLayout.VERTICAL)
    recyclerView.addItemDecoration(itemDecoration)
    recyclerView.adapter = adapter


    adapter.setCarParts(dataManager.fetchCarMocks())
    presenter.fetchMocks()

    ItemClickSupport.addTo(recyclerView)
      .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
        override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
          presenter.onItemClicked(position)
        }
      })
      .setOnItemLongClickListener(object : ItemClickSupport.OnItemLongClickListener{
        override fun onItemLongClicked(recyclerView: RecyclerView?, position: Int,
          v: View?): Boolean {
          TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
          return true
        }
      })

    //TODO fil the fragment with logic and add some listner or some other feature

    return view
  }

  override fun onDestroy() {
    presenter.unbind()
    super.onDestroy()
  }

  // MVR implementation

  override fun showPartList(partList: List<CarPart>) {
    adapter.setCarParts(partList)
  }

  override fun handleSingleClick(position: Int?, carPart: CarPart) {
    val bundle = Bundle()
    bundle.putParcelable(AppConstants.CAR_PART_BUNDLE.key,carPart)

    val partDetailsFragment = PartDetailsFragment.newInstance(bundle)
    val manager = childFragmentManager
    partDetailsFragment.show(manager, "PartDetails")
  }

}