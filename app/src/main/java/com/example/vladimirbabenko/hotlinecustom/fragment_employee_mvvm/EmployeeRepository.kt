package com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.CountDownTimer
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.data.EmployeeMocks
import com.example.vladimirbabenko.hotlinecustom.room.AppDatabaseRoom
import com.example.vladimirbabenko.hotlinecustom.room.Employee

/*
* Клксс репозиторий возвращает LiveData на которую подписывается ViewModel
* Соответсвенно при загрузке новой информации мы будем писать информацию напрямую в базу данных.
* А на базу данных включена подписка
*
* */

class EmployeeRepository {

  private val INSTANCE = this
  private val databaseRoom = AppDatabaseRoom.create

  companion object {
    fun newInstance(): EmployeeRepository {
      val fragment = EmployeeRepository()
      return fragment
    }
  }

  private var dataMAnager = DataManager.create
  private var listLiveDataEmployee = MutableLiveData<List<Employee>>()

  // This fun migeht consist persistance. Might be realized vith room
  fun getCashedEmployee(): LiveData<List<Employee>> {
    return databaseRoom.employeeDao.getAllEmployee()
  }

  /*
  * if internet connection is present... No in "onSuccesCallback download new data to dataBase  "
  * This fun shold download data to room. and then room will send live data to
  * */

  fun uploadData() {

    if (true) {

      databaseRoom.employeeDao.isertEmployee(
        EmployeeMocks().fetchMocks()) // Log.d("TAGEMPLOYEE", databaseRoom.employeeDao.getAllEmployee().toString())
    }
  }

  fun downloadNewRandomEmployee() {
    val timer = object : CountDownTimer(1500, 500) {
      override fun onTick(millisUntilFinished: Long) {
      }

      override fun onFinish() {
        databaseRoom.employeeDao.isertEmployee(listOf(EmployeeMocks().getNewRandomEmployee()))
      }
    }
    timer.start()
  }
}