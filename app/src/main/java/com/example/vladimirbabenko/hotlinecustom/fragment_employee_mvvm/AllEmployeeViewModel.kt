package com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.room.Employee

/*
* Architecture components return singlenton
* */
class AllEmployeeViewModel : ViewModel(), LifecycleObserver {

  private val TAG = this.javaClass.simpleName
  private var repository: EmployeeRepository? = EmployeeRepository()
  // LiveData for
  //private var mEmployeeLiveData = MutableLiveData<List<Employee>>()

  init{
    Log.d(TAG, "Initializing ")
  }

  /*
  * returns link to employeeLiveData from room db
  * */
  fun getAllEmployee(): LiveData<List<Employee>> {
    return repository!!.getCashedEmployee()
  }

  fun downloadNewRandomEmployee(){
    repository!!.downloadNewRandomEmployee()

  }

  /*
  * This fun calls when ViewModel finishes it's lifecycle.
  * Resources should be cleaned
  * */
  override fun onCleared() {
    repository = null
    super.onCleared()

  }

  // Lifecycles callbacks
  @OnLifecycleEvent(value = Lifecycle.Event.ON_START) private fun onViewStart() {
    Log.d(TAG, "Fragment Employee is started")
    repository!!.uploadData()
  }

}


