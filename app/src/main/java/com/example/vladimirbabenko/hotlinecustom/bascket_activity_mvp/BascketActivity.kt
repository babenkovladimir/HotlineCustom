package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import kotlinx.android.synthetic.main.activity_bascket.rvBascketList

class BascketActivity : BaseActivity(), IBascketView {

  val presenter = BascketPresenter()
  val adapter = BascketRVadapter()
  lateinit var layoutManager:LinearLayoutManager
  lateinit var recyclerView: RecyclerView

  init {
    val adapter = BascketRVadapter()
    Log.d("TAGTEST", "on IniBlock")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    Log.d("TAGTEST", "onCreate")
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bascket)

    presenter.bind(this)
    setupUi()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.unbind()
  }

  // Private helpers

  private fun setupUi() {
    layoutManager = LinearLayoutManager(this.applicationContext, LinearLayout.VERTICAL, false)



    rvBascketList.layoutManager = layoutManager
    rvBascketList.adapter = adapter
    presenter.fetchMocks()
  }

  // IBasketView implementation

  override fun showList(set: Set<BascketItem>) {
    adapter.setItems(set)
  }
}
