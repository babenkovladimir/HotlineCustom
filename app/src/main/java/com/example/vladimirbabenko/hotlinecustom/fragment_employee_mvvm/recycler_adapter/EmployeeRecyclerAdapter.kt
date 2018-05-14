package com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.recycler_adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.recycler_adapter.EmployeeRecyclerAdapter.EmployeeHolder
import com.example.vladimirbabenko.hotlinecustom.room.Employee

class EmployeeRecyclerAdapter() : RecyclerView.Adapter<EmployeeHolder>() {

  private var employeeList: List<Employee> = mutableListOf<Employee>()

  fun setEmployeeList(employeeList: List<Employee>) {
    this.employeeList = employeeList
    notifyDataSetChanged()
  }

  // Life
  override fun onCreateViewHolder(parent: ViewGroup,
    viewType: Int): EmployeeHolder { // not using w=in DataBinding
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)

//    val layoutInflater = LayoutInflater.from(parent.context)
//    val binding: ViewDataBinding =
//      DataBindingUtil.inflate(layoutInflater, R.layout.item_employee, parent, false)

    return EmployeeHolder(view)
  }

  override fun getItemCount(): Int = employeeList.size

  override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
    holder.bind(employeeList.elementAt(position))
  }

  class EmployeeHolder(var binding: View) : RecyclerView.ViewHolder(binding) {

    fun bind(employee: Any) {

      //      with(itemView) {
      //        tvEmployeeId.text = employee.id.toString()
      //        tvEmployeeName.text = employee.name
      //        tvEmployeeSecondName.text = employee.secondName
      //        tvEmployeeSalary.text = "$ " + employee.salary.toString()
      //      }
     // binding.setVariable()
      //binding.executePendingBindings()
    }
  }
}
