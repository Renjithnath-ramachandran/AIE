package com.aie.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */

@Entity(tableName = "department")
data class Department(
    @PrimaryKey(autoGenerate = true) val dept_id: Int,
    @ColumnInfo(name = "dept_name") val name: String?
)