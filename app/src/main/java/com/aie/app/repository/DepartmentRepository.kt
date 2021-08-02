package com.aie.app.repository

import android.app.Application
import android.os.AsyncTask
import com.aie.app.database.EmployeeDatabase
import com.aie.app.database.dao.DepartmentDao
import com.aie.app.database.entity.Department


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */
class DepartmentRepository(application: Application) {

    private val departmentDao: DepartmentDao

    init {
        val employeeDatabase = EmployeeDatabase.getInstance(application)
        departmentDao = employeeDatabase.departmentDao()
        deleteDepartmentAsyncTask(departmentDao).execute()
    }

    fun deleteDepartment() {
        departmentDao.deleteAllDepertments()
    }

    fun insertDepartment(department: Department) {
        insertDepartmentAsyncTask(departmentDao).execute(department)
    }

    private class insertDepartmentAsyncTask internal constructor(private val mDepartmentDAO: DepartmentDao): AsyncTask<Department, Void, Void>() {
        override fun doInBackground(vararg params: Department?): Void? {
            mDepartmentDAO.insertAll(params[0])
            return null
        }
    }

    private class deleteDepartmentAsyncTask internal constructor(private val mDepartmentDAO: DepartmentDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            mDepartmentDAO.deleteAllDepertments()
            return null
        }
    }
}