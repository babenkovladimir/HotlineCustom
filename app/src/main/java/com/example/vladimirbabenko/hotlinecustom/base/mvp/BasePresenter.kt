package com.example.vladimirbabenko.hotlinecustom.base.mvp

abstract class BasePresenter<V : IView> {

  private var view: V? = null

  fun getView() = view

  fun bind(v: V) {
    this.view = v
  }

  fun unbind() {
    this.view = null
  }
}