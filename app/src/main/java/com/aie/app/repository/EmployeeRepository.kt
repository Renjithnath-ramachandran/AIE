package com.aie.app.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.aie.app.database.EmployeeDatabase
import com.aie.app.database.dao.DepartmentDao
import com.aie.app.database.dao.EmployeeDao
import com.aie.app.database.entity.Employee


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */
class EmployeeRepository(application: Application) {

    private val employeeDao: EmployeeDao
    private val liveEmployeeData: LiveData<List<Employee>>

    init {
        val employeeDatabase = EmployeeDatabase.getInstance(application)
        employeeDao = employeeDatabase.employeeDao()
        deleteEmployeeAsyncTask(employeeDao).execute()
        liveEmployeeData = employeeDao.getAllEmployees()
    }

    fun deleteEmployee() {
        employeeDao.deleteAllEmployees()
    }

    fun getAllEmployees(): LiveData<List<Employee>> {
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