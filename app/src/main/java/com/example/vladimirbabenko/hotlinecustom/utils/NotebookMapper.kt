package com.example.vladimirbabenko.hotlinecustom.utils

import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook

class NotebookMapper:BaseMapper<NoteBook, BascketItem> {
  override fun transform(input: NoteBook): BascketItem {
    return BascketItem(input.id, input.model, input.price, input.photUrl.toString(), 1)
  }
}