package com.aie.app.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aie.app.database.dao.DepartmentDao
import com.aie.app.database.entity.Department
import com.aie.app.database.entity.Employee
import com.aie.app.repository.DepartmentRepository
import com.aie.app.repository.EmployeeRepository


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */
class EmployeeVM(application: Application) : AndroidViewModel(application) {

    private val employeeRepository: EmployeeRepository
    private val departmentRepository: DepartmentRepository

    internal val allEmployees: LiveData<List<Employee>>

    init {
        employeeRepository = EmployeeRepository(application)
        departmentRepository = DepartmentRepository(application)

//        employeeRepository.deleteEmployee()
//        departmentRepository.deleteDepartment()

        insertEmployee(Employee(0, "Alisha J", "22-Feb-13", 2, 2))
        insertEmployee(Employee(0, "John Mathew", "13-Sep-14", 2, 4))
        insertEmployee(Employee(0, "Syed Ali", "26-Jun-16", 2, 3))
        insertEmployee(Employee(0, "Ramesh G", "01-Nov-17", 2, 1))

        insertDepartment(Department(0, "Operations"))
        insertDepartment(Department(0, "Finance"))
        insertDepartment(Department(0, "HR"))
        insertDepartment(Department(0, "Managing Director"))

        allEmployees = employeeRepository.getAllEmployees()
    }

    fun insertEmployee(employee: Employee) {
        employeeRepository.insertEmployee(employee)
    }

    fun insertDepartment(department: Department) {
        departmentRepository.insertDepartment(department)
    }

}
