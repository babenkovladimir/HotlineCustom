package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity

class BascketActivity : BaseActivity(), IBascketView {

    val presenter = BascketPresenter()
    val adapter = BascketRVadapter()
    lateinit var recyclerView:RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bascket)

    presenter.bind(this)

  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.unbind()
  }
}
