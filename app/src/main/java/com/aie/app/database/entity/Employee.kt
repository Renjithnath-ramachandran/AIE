package com.aie.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */



@Entity(tableName = "employee", foreignKeys = arrayOf(
    ForeignKey(entity = Department::class, parentColumns = arrayOf(
    "dept_id"), childColumns = arrayOf("department_id"), onDelete = ForeignKey.CASCADE
    )
))

//@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "emp_name") val emp_name: String?,
    @ColumnInfo(name = "joining_date") val joining_date: String?,
    @ColumnInfo(name = "manager_id") val manager_id: Int?,
    @ColumnInfo(name = "department_id") val department_id: Int?
)