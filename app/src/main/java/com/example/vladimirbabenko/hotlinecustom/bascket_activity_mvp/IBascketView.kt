package com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp

import com.example.vladimirbabenko.hotlinecustom.base.mvp.IView
import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem

interface IBascketView : IView {

  fun showList(set: Set<BascketItem>)
}