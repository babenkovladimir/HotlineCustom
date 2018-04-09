package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.NoteBookRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_notebook_list.view.rvNoteBookRecyclerView

class NoteBookListFragment():Fragment() {

  lateinit var dataManager:DataManager

  companion object {
    val title:String = "NoteBooks"
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

    val view: View = inflater.inflate(R.layout.fragment_notebook_list, container, false)
    view.rvNoteBookRecyclerView.layoutManager = LinearLayoutManager(container!!.context)


    val adapter: NoteBookRecyclerViewAdapter = NoteBookRecyclerViewAdapter()
    view.rvNoteBookRecyclerView.adapter = adapter

    adapter.setNoteBooks(dataManager.fetchMocks())
    return view
  }




}