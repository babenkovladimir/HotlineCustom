package com.example.vladimirbabenko.hotlinecustom.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.example.vladimirbabenko.hotlinecustom.base.App
import com.example.vladimirbabenko.hotlinecustom.base.App.Companion
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED

@Database(entities = arrayOf(Employee::class), version = 1) abstract class AppDatabaseRoom :
  RoomDatabase() {

  private val TAG = this.javaClass.simpleName
  abstract val employeeDao: EmployeeDao

  init {
    Log.d(TAG, "Initializing")
  }

  /*
  * First way to get Database singlenton. it returns itself.
  * Second way - initialize Instance in Application class
  * */
  companion object {
    private var INSTANCE: AppDatabaseRoom? = null

    fun getInstance(context: Context): AppDatabaseRoom? {
      if (INSTANCE == null) {
        synchronized(AppDatabaseRoom::class) {
          INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabaseRoom::class.java,
            "database.db")
            .allowMainThreadQueries() // Позволяет делать запросы в базу данных в UI потоке
            .build()
        }
      }
      return INSTANCE
    }

    val create: AppDatabaseRoom by lazy(SYNCHRONIZED) {
      Room.databaseBuilder(App.applicationContext(), AppDatabaseRoom::class.java, "roomdata.db")
        .allowMainThreadQueries().build()
    }

    fun destroyInstance() {
      INSTANCE = null
    }
  }
}