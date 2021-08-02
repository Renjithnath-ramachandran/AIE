package com.aie.app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aie.app.database.entity.Employee


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */
@Dao
interface EmployeeDao {

    @Insert
    fun insertAll(vararg employee: Employee?)

    @Query("SELECT * FROM employee")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Query("DELETE FROM employee")
    fun deleteAllEmployees()
}