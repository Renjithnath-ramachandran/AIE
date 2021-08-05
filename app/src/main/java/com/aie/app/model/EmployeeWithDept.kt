package com.aie.app.model

import androidx.room.Embedded
import androidx.room.Relation
import com.aie.app.database.entity.Department
import com.aie.app.database.entity.Employee


/**
 * Created by Renjithnath R on 05/08/21.
 * renjithnath26@gmail.com
 */

data class EmployeeWithDept(
    @Embedded val employee: Employee,
    @Relation(
        parentColumn = "department_id",
        entityColumn = "dept_id"
    )
    val department: List<Department>
)