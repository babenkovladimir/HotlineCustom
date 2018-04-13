package com.example.vladimirbabenko.hotlinecustom.data.mocks

/*
* List<out T> - readOnly Collection
* */

interface IRepositoryMock<out T> {
  fun fetchMocks():List<out T>
}