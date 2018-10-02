package com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.recycler_adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.recycler_adapter.EmployeeRecyclerAdapter.EmployeeHolder
import com.example.vladimirbabenko.hotlinecustom.room.Employee

class EmployeeRecyclerAdapter() : RecyclerView.Adapter<EmployeeHolder>() {

  private var employeeList: List<Employee> = mutableListOf()

  fun setEmployeeList(employeeList: List<Employee>) {
    this.employeeList = employeeList
    notifyDataSetChanged()
  }

  // Life
  override fun onCreateViewHolder(parent: ViewGroup,
    viewType: Int): EmployeeHolder { // not using w=in DataBinding

    val layoutInflater = LayoutInflater.from(parent.context)
    val binding: ViewDataBinding =
      DataBindingUtil.inflate(layoutInflater, R.layout.item_employee, parent, false)

    return EmployeeHolder(binding)
  }

  override fun getItemCount(): Int = employeeList.size

  override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
    holder.bind(employeeList.elementAt(position))
  }

  class EmployeeHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(employee: Any) {

      binding.setVariable(BR.employee, employee)
      binding.executePendingBindings()
    }
  }
}
