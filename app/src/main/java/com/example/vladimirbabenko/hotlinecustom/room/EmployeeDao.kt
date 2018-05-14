package com.example.vladimirbabenko.hotlinecustom.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao interface EmployeeDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) fun isertEmployee(employeeList: List<Employee>)

  @Update(onConflict = OnConflictStrategy.REPLACE) fun updateEmployee(vararg employee: Employee)

  @Delete fun deleteEmployee(vararg employee: Employee)

  @Query(value = "SELECT * FROM employee") fun getAllEmployee():LiveData<List<Employee>>

  @Query(value = "SELECT * FROM employee WHERE id = :id") fun getEmployeeById(id:Long): Employee

  @Query(value = "DELETE FROM employee") fun deleteAll()

}