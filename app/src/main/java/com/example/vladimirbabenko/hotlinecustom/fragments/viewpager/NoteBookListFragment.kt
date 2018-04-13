package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.NoteBookRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_notebook_list.view.rvNoteBookRecyclerView
import kotlinx.android.synthetic.main.fragment_notebook_list.view.srlSwipeRefresh

class NoteBookListFragment : Fragment() {

  // for view
  private lateinit var returnView: View
  private lateinit var rvNoteBookRecyclerView: RecyclerView
  private lateinit var adapter: NoteBookRecyclerViewAdapter
  private lateinit var layoutManager: LinearLayoutManager

  // data
  private lateinit var dataManager: DataManager
  private lateinit var notebooks: MutableList<NoteBook>

  // SwipeRefreshLayout
  lateinit var swipeRefreshLayout: SwipeRefreshLayout
  private lateinit var mRunnable: Runnable
  private var mHandler: Handler

  init {
    mHandler = Handler()
  }

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
    dataManager = DataManager.create()
    notebooks = (dataManager.fetchMocks()).toMutableList()
    notebooks = (dataManager.fetchMocks()).toMutableList()

    dataManager.casheNoteBook.saveList(dataManager.fetchMocks())
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    returnView = inflater.inflate(R.layout.fragment_notebook_list, container, false)
    rvNoteBookRecyclerView = returnView.rvNoteBookRecyclerView

    layoutManager = LinearLayoutManager(container!!.context, LinearLayout.VERTICAL, false)
    rvNoteBookRecyclerView.layoutManager = layoutManager

    adapter = NoteBookRecyclerViewAdapter()
    rvNoteBookRecyclerView.adapter = adapter

    val itemDecoration =
      DividerItemDecoration(rvNoteBookRecyclerView.context, LinearLayout.VERTICAL)
    rvNoteBookRecyclerView.addItemDecoration(itemDecoration)
    adapter.setNoteBooks(notebooks)

    swipeRefreshLayout = returnView.srlSwipeRefresh

    setupRefreshLayout()

    return returnView
  }

  //todo - question about runnable in mHandler
  private fun setupRefreshLayout() {
    swipeRefreshLayout.setOnRefreshListener {
      mRunnable = Runnable() {
        // Download new Books

        swipeRefreshLayout.isRefreshing = false
      }

      mHandler.postDelayed(mRunnable, 1500)
    }
  }
}

//inner class MyAsynckLoader() : AsyncTask<Void, Int, NoteBook>() {
//
//  override fun doInBackground(vararg params: Void?): NoteBook {
//    publishProgress(30)
//    Thread.sleep(500)
//    publishProgress(20)
//    Thread.sleep(500)
//    publishProgress(10)
//
//    return NoteBook("AlienWare", "AW 1556", 1000, "i7 4820HK", "1080TI", "samsung 960 PRO", "")
//  }
//
//  override fun onProgressUpdate(vararg values: Int?) {
//    super.onProgressUpdate(*values)
//    Toast.makeText(context, "IsLoading " + values[0].toString(), Toast.LENGTH_SHORT)
//  }
//
//  override fun onPostExecute(notebook: NoteBook?) {
//    super.onPostExecute(notebook)
//    notebooks.add(notebook!!)
//    adapter.setNoteBooks(notebooks)
//    srlSwipeRefresh.isRefreshing = false
//  }



