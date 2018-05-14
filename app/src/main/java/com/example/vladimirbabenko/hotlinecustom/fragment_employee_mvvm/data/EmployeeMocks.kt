package com.example.vladimirbabenko.hotlinecustom.fragment_employee_mvvm.data

import com.example.vladimirbabenko.hotlinecustom.room.Employee
import java.util.Random

class EmployeeMocks : IRepository<Employee> {
  // List of id for employee
  private val id = MutableList<Long>(20, { li -> 2 * li + li + 1L })

  private val name =
    listOf<String>("Vladimir", "Andrey", "Pavel", "Voldemar", "Yuriy", "Seroga", "Artyom", "Sveta",
      "Olya", "Alfred", "Dima", "Anton", "Serg", "Olexander", "Frank", "Ditrih", "Lars", "James",
      "Kirk", "Udo")
  private val secondName =
    listOf<String>("Babenko", "Ivanov", "Sidorv", "Ivanov", "Malkovich", "Ivanov", "Malkovich",
      "Sidorv", "Malkovich", "Ivanov", "Jakaruta", "Sidorv", "Jakaruta", "Ivanov", "Jakaruta",
      "Malkovich", "Sidorv", "Ivanov", "Jakaruta", "Malkovich")
  private val salary =
    listOf<Int>(1500, 1400, 800, 400, 650, 750, 980, 487, 198, 1544, 3211, 4544, 2100, 3200, 980,
      147, 254, 658, 111, 784)

  override fun fetchMocks(): List<Employee> {
    val list = mutableListOf<Employee>()

    for (i in 0..id.size - 1) list.add(

      Employee(id = id.elementAt(i), name = name.elementAt(i), secondName = secondName.elementAt(i),
        salary = salary.elementAt(i)))
    return list
  }

  override fun getNewRandomEmployee(): Employee {

    return Employee(
      id = (id.elementAt(Random().nextInt(19)) + Random(1000).nextInt() * 248),
      name = name.elementAt(Random().nextInt(19)),
      secondName = secondName.elementAt(Random().nextInt(19)),
      salary = salary.elementAt(Random().nextInt(19)))
  }
}
