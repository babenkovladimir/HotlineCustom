package com.example.vladimirbabenko.hotlinecustom.utils.mappers

interface BaseMapper<in A, out B> {
  fun transform(input: A) : B
}