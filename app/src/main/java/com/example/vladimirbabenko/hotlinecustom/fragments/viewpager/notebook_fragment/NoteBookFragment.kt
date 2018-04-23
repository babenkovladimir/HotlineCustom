package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.notebook_fragment.NoteBookDetailsFragment
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.notebook_fragment.NoteBookRecyclerViewAdapter
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.ItemClickSupport
import kotlinx.android.synthetic.main.fragment_notebook_list.view.rvNoteBookRecyclerView
import kotlinx.android.synthetic.main.fragment_notebook_list.view.srlSwipeRefresh

class NoteBookFragment : Fragment() {

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

    fun newInstance(bundle: Bundle?): NoteBookFragment {
      val fragment = NoteBookFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataManager = DataManager.create // notebooks = (dataManager.fetchMocks()).toMutableList()
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

    if (dataManager.prefs.withInternetConnection) {
      dataManager.saveCasheNoteBook(dataManager.fetchMocks())
    }
    notebooks = dataManager.getCasheNotebook()
    adapter.setNoteBooks(notebooks)

    ItemClickSupport.addTo(rvNoteBookRecycler)
      .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
        override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
          YoYo.with(Techniques.ZoomInUp).duration(300)
            .onEnd({ showNotebookDetailsFragment(notebooks.elementAt(position)) }).playOn(v);
        }
      })

    setupRefreshLayout()
    return returnView
  }

  // Private Helpers

  private fun showNotebookDetailsFragment(notebook: NoteBook) {
    val bundle = Bundle()
    bundle.putParcelable(AppConstants.NOTEBOOK_PART_BUNDL.key, notebook)
    val manager = fragmentManager
    val notebookDetailsFragment = NoteBookDetailsFragment.newInstance(bundle)
    notebookDetailsFragment.show(manager, "NoteBookDetailsFragment")
  }

  private fun setupRefreshLayout() {
    swipeRefreshLayout = returnView.srlSwipeRefresh
    swipeRefreshLayout.setOnRefreshListener {
      mRunnable = Runnable() {
        // Download new Books
        Toast.makeText(context, "New Notebook added", Toast.LENGTH_SHORT).show()
        swipeRefreshLayout.isRefreshing = false
      }

      mHandler.postDelayed(mRunnable, 1500)

    }
  }
}



