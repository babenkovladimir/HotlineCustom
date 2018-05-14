package com.example.vladimirbabenko.hotlinecustom.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/*
* Room database is sensetive to UpperCase in tables
*
*/
@Entity(tableName = "employee")
data class Employee (
  @PrimaryKey var id: Long? = 0,
  @ColumnInfo(name = "name") var name:String? = "Ivanov",
  @ColumnInfo(name = "secondname") var secondName: String? = "Ivanovich",
  @ColumnInfo(name = "salary") var salary: Int? = 0
)
