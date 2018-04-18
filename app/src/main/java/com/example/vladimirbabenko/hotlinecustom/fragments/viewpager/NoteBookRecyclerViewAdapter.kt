package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_notebook.view.ivNotebookImage
import kotlinx.android.synthetic.main.item_notebook.view.tvNoteBrand
import kotlinx.android.synthetic.main.item_notebook.view.tvNoteHdd
import kotlinx.android.synthetic.main.item_notebook.view.tvNoteModel
import kotlinx.android.synthetic.main.item_notebook.view.tvNotePrice
import kotlinx.android.synthetic.main.item_notebook.view.tvNoteProcessor
import kotlinx.android.synthetic.main.item_notebook.view.tvNoteVideoCard

class NoteBookRecyclerViewAdapter :
  RecyclerView.Adapter<NoteBookRecyclerViewAdapter.NotebookViewHolder>() {

  private var notebooks: List<NoteBook> = emptyList() // Here we can use Delegates Observable

  fun setNoteBooks(notebooks: List<NoteBook>) {
    this.notebooks = notebooks
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder {
    val v = LayoutInflater.from(parent.context).inflate(R.layout.item_notebook, parent, false)
    return NotebookViewHolder(v)
  }

  override fun getItemCount(): Int = notebooks.size

  override fun onBindViewHolder(holder: NotebookViewHolder, position: Int) {
    holder.bind(notebooks[position])
  }

  class NotebookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(noteBook: NoteBook) {

      Picasso.get()
        .load(noteBook.photUrl)
        .fit()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(itemView.ivNotebookImage)

      itemView.tvNoteBrand.text = noteBook.brand
      itemView.tvNoteModel.text = noteBook.model
      itemView.tvNoteProcessor.text = noteBook.processor
      itemView.tvNoteVideoCard.text = noteBook.videoCard
      itemView.tvNoteHdd.text = noteBook.hdd
      itemView.tvNotePrice.text = noteBook.price.toString()
    }
  }
}