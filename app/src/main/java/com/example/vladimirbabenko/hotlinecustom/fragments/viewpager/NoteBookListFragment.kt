package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.NoteBookRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_notebook_list.view.rvNoteBookRecyclerView

class NoteBookListFragment() : Fragment() {

  lateinit var dataManager: DataManager

  companion object {
    val title: String = "NoteBooks"
      get

    fun newInstance(bundle: Bundle?): NoteBookListFragment {
      val fragment = NoteBookListFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataManager = DataManager.create(context!!)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    //TODO How to describe???
    val view: View = inflater.inflate(R.layout.fragment_notebook_list, container, false)

    val layoutManager = LinearLayoutManager(container!!.context, LinearLayout.VERTICAL, false)
    view.rvNoteBookRecyclerView.layoutManager = layoutManager

    val adapter = NoteBookRecyclerViewAdapter()
    view.rvNoteBookRecyclerView.adapter = adapter

    val dividerItemDecoration = DividerItemDecoration(view.rvNoteBookRecyclerView.context, LinearLayout.VERTICAL)
    view.rvNoteBookRecyclerView.addItemDecoration(dividerItemDecoration)
    adapter.setNoteBooks(dataManager.fetchMocks())
    return view
  }
}