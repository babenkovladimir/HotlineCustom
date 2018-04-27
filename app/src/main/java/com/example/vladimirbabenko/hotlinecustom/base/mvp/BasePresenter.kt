package com.example.vladimirbabenko.hotlinecustom.base.mvp

import com.example.vladimirbabenko.hotlinecustom.data.DataManager

abstract class BasePresenter<V : IView> {

  private var view: V? = null
  protected val mDataManager = DataManager.create

  fun getView() = view

  open fun bind(v: V) {
    this.view = v
  }

  open fun unbind() {
    this.view = null
  }
}