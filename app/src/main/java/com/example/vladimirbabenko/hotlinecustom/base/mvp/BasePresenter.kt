package com.example.vladimirbabenko.hotlinecustom.base.mvp

abstract class BasePresenter<V : IView> {

  private var v: V? = null
    get

  fun bind(v: V) {
    this.v = v
  }

  fun unbind() {
    this.v = null
  }
}