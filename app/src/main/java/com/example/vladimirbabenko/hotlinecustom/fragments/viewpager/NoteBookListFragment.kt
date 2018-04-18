package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.Data
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
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.NoteBookRecyclerViewAdapter
import com.example.vladimirbabenko.hotlinecustom.utils.InternetConnectionHelper
import com.example.vladimirbabenko.hotlinecustom.utils.ItemClickSupport
import kotlinx.android.synthetic.main.fragment_notebook_list.view.rvNoteBookRecyclerView
import kotlinx.android.synthetic.main.fragment_notebook_list.view.srlSwipeRefresh

class NoteBookListFragment : Fragment() {

  // for view
  private lateinit var returnView: View
  private lateinit var rvNoteBookRecycler: RecyclerView
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
    dataManager = DataManager.create
    // notebooks = (dataManager.fetchMocks()).toMutableList()
    //notebooks = (dataManager.getCasheNotebook()).toMutableList()
    dataManager.saveCasheNoteBook(dataManager.fetchMocks())
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    // Create view element and detect recyclerView
    returnView = inflater.inflate(R.layout.fragment_notebook_list, container, false)
    rvNoteBookRecycler = returnView.rvNoteBookRecyclerView

    // Create LayoutManager and setup RecyclerView with its manager
    layoutManager = LinearLayoutManager(container!!.context, LinearLayout.VERTICAL, false)
    rvNoteBookRecycler.layoutManager = layoutManager

    // Create and setup adapter to RecyclerView
    adapter = NoteBookRecyclerViewAdapter()
    rvNoteBookRecycler.adapter = adapter

    // Create ItemDecoration and setup RV with ItemDecoration
    val itemDecoration = DividerItemDecoration(rvNoteBookRecycler.context, LinearLayout.VERTICAL)
    rvNoteBookRecycler.addItemDecoration(itemDecoration)

    //
    //Check for first run????
    //if (InternetConnectionHelper.isConnection()) {
    if (dataManager.prefs.withInternetConnection) {
      dataManager.saveCasheNoteBook(dataManager.fetchMocks())
    }

    ItemClickSupport.addTo(rvNoteBookRecycler).setOnItemClickListener(object : ItemClickSupport.OnItemClickListener{
      override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
        YoYo.with(Techniques.DropOut)
          .duration(700)
          .playOn(v);
      }
    })


    adapter.setNoteBooks(dataManager.getCasheNotebook())

    setupRefreshLayout()
    return returnView
  }

  //todo - question about runnable in mHandler
  private fun setupRefreshLayout() {
    swipeRefreshLayout = returnView.srlSwipeRefresh
    swipeRefreshLayout.setOnRefreshListener {
      mRunnable = Runnable() {
        // Download new Books
        swipeRefreshLayout.isRefreshing = false
      }

      mHandler.postDelayed(mRunnable, 1500)

      Toast.makeText(context, "Is refreshing lyambda works", Toast.LENGTH_SHORT).show()
    }
  }
}



