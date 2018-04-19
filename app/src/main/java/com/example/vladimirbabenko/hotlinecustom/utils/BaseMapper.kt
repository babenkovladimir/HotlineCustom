package com.example.vladimirbabenko.hotlinecustom.utils

interface BaseMapper<in A, out B> {
  fun transform(input: A) : B
}