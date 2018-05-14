package com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.data.HeaderTitle
import com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.recycler_adapter.EmployeeRecyclerAdapter
import com.example.vladimirbabenko.hotlinecustom.room.Employee
import kotlinx.android.synthetic.main.fragment_employee.srlRefreshLayout
import kotlinx.android.synthetic.main.fragment_employee.view.rvEmplyee

class EmployeeFragment() : Fragment() {

  private var TAG = this.javaClass.simpleName
  private var mAdapter: EmployeeRecyclerAdapter? = null
  private var recyclerView: RecyclerView? = null
  private lateinit var viewModel: AllEmployeeViewModel

  init {
    Log.d(TAG, "initializing")
  }

  companion object {
    val title = "Employee"

    fun newInstance(bundle: Bundle? = null): EmployeeFragment {
      val fragment = EmployeeFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  /*
  * In this fun we get instance of viewModel
  * and sunscribe it for FEmployeeFragment lifecycle
  *
  * */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(AllEmployeeViewModel::class.java)
    lifecycle.addObserver(viewModel)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_employee, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    //setupDataBinding()
    setupRecyclerView(view, savedInstanceState)
    setupSwipeRefreshLayout(view)
  }


  /*
  * Класс отвечающий за
  * */
  private fun setupDataBinding() {
    // ObservableField
    //val headerTitle = ObservableField(HeaderTitle())
    val headerTitle = HeaderTitle()
//    val binding:FragmentEmployeeBinding = DataBindingUtil.setContentView(activity!!,  R.layout.fragment_employee)
//    binding.headertitle = headerTitle
//    binding.invalidateAll()// not using vith LiveData???
  }

  private fun setupSwipeRefreshLayout(view: View) {
    srlRefreshLayout.setOnRefreshListener { viewModel.downloadNewRandomEmployee() }
  }

  // private helpers

  private fun setupRecyclerView(view: View, savedInstanceState: Bundle?) {
    mAdapter = EmployeeRecyclerAdapter()
    recyclerView = view.rvEmplyee

    recyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    recyclerView!!.adapter = mAdapter

    // gets all employee from server
    viewModel.getAllEmployee().observe(this, object : Observer<List<Employee>> {
      override fun onChanged(t: List<Employee>?) {
        Log.d(TAG, t.toString())
        if (srlRefreshLayout.isRefreshing) srlRefreshLayout.isRefreshing = false
        mAdapter!!.setEmployeeList(t!!)
      }
    })
  }

  /*
  * I"am no shure if i have to remove observer
  * */
  override fun onDestroy() {
    lifecycle.removeObserver(viewModel)
    super.onDestroy()
  }
}