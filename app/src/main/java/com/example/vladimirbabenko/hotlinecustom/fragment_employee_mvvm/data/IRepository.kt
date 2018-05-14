package com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.data

interface IRepository<T> {

  fun fetchMocks(): List<T>
  fun getNewRandomEmployee(): T
}