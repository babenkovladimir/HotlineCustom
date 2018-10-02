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
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.event_bus.Events
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.notebook_fragment_god_object.NoteBookDetailsFragment
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.notebook_fragment_god_object.NoteBookRecyclerViewAdapter
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants
import com.example.vladimirbabenko.hotlinecustom.utils.ItemClickSupport
import com.squareup.otto.Subscribe
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

  // Bus
  private var bus:GlobalBus

  // SwipeRefreshLayout
  lateinit var swipeRefreshLayout: SwipeRefreshLayout
  private lateinit var mRunnable: Runnable
  private var mHandler: Handler

  init {
    mHandler = Handler()
    bus = GlobalBus.instance
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
    bus.register(this)
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
    //val itemDecoration = DividerItemDecoration(rvNoteBookRecycler.context, LinearLayout.VERTICAL)
    //rvNoteBookRecycler.addItemDecoration(itemDecoration)

    //
    //Check for first run????

    if (dataManager.prefs.withInternetConnection) {
      dataManager.saveCasheNoteBook(dataManager.fetchMocks())
    }
    notebooks = dataManager.getCasheNotebook() as MutableList<NoteBook>
    adapter.setNoteBooks(notebooks, getChosenList())
    Log.d("TAGRRRR", getChosenList().toString())

    ItemClickSupport.addTo(rvNoteBookRecycler)
      .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
        override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
          YoYo.with(Techniques.FadeIn).duration(300)
            .onEnd({ showNotebookDetailsFragment(notebooks.elementAt(position)) }).playOn(v);
        }
      })

    setupRefreshLayout()
    return returnView
  }

  override fun onResume() {
    Log.d("TAGRRRR", getChosenList().toString())
    adapter.setNoteBooks(notebooks, getChosenList())
    super.onResume()
  }

  override fun onDestroy() {
    bus.unregister(this)
    super.onDestroy()
  }

  // Private Helpers

  private fun showNotebookDetailsFragment(notebook: NoteBook) {
    val bundle = Bundle()
    bundle.putParcelable(AppConstants.NOTEBOOK_PART_BUNDL.key, notebook)
    bundle.putBoolean("NotebookIsInBascket", getChosenList().contains(notebook.id))
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

  private fun getChosenList(): MutableList<Int>{
    var chosenList = mutableListOf<Int>()
    val items = dataManager.getFromBasket()
    for(item in items) chosenList.add(item.id!!)
    return chosenList
  }

  @Subscribe fun refreshAdapter(event: Events.NotebookFragmentRefresh){
    Log.d("TAGNOTE", "in Refresh adapter")
    adapter.setNoteBooks(notebooks, getChosenList())

  }
}



