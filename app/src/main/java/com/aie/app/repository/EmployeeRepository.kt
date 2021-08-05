package com.aie.app.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.aie.app.database.EmployeeDatabase
import com.aie.app.database.dao.DepartmentDao
import com.aie.app.database.dao.EmployeeDao
import com.aie.app.database.entity.Employee
import com.aie.app.model.EmployeeWithDept


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */
class EmployeeRepository(application: Application) {

    private val employeeDao: EmployeeDao
//    private lateinit var liveEmployeeData: LiveData<List<Employee>>
    private lateinit var liveEmployeeData: LiveData<List<EmployeeWithDept>>

    init {
        val employeeDatabase = EmployeeDatabase.getInstance(application)
        employeeDao = employeeDatabase.employeeDao()
//        deleteEmployeeAsyncTask(employeeDao).execute()

    }

    fun deleteEmployee() {
        employeeDao.deleteAllEmployees()
    }

//    fun getAllEmployees(): LiveData<List<Employee>> {
//        liveEmployeeData = employeeDao.getAllEmployees()
//        return liveEmployeeData
//    }

    fun getAllEmployees(): LiveData<List<EmployeeWithDept>> {
        liveEmployeeData = employeeDao.getEmployeeWithDept()
        return liveEmployeeData
    }

    fun insertEmployee(employee: Employee) {
        insertEmployeeAsyncTask(employeeDao).execute(employee)
    }

    private class insertEmployeeAsyncTask internal  constructor(private val mEmployeeTaskDAO: EmployeeDao): AsyncTask<Employee, Void, Void>() {
        override fun doInBackground(vararg params: Employee?): Void? {
            mEmployeeTaskDAO.insertAll(params[0])
            return null
        }
    }

    private class deleteEmployeeAsyncTask internal constructor(private val mEmployeeTaskDAO: EmployeeDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            mEmployeeTaskDAO.deleteAllEmployees()
            return null
        }
    }
}