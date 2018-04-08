package com.example.vladimirbabenko.hotlinecustom.data

/*
* List<out T> - readOnly Collection
* */

interface IRepositoryMock<out T> {
  fun fetchMocks():List<out T>
}