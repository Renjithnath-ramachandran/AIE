package com.aie.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aie.app.database.dao.DepartmentDao
import com.aie.app.database.dao.EmployeeDao
import com.aie.app.database.entity.Department
import com.aie.app.database.entity.Employee


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */
@Database(entities = [Employee::class, Department::class], version = 2)
abstract class EmployeeDatabase: RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao
    abstract fun departmentDao(): DepartmentDao

    companion object {
        private var instance: EmployeeDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): EmployeeDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, EmployeeDatabase::class.java,
                    "database-app"
                ).build()

            return instance!!

        }
    }
}