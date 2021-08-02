package com.aie.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aie.app.database.entity.Department


/**
 * Created by Renjithnath R on 02/08/21.
 * renjithnath26@gmail.com
 */


@Dao
interface DepartmentDao {

    @Insert
    fun insertAll(vararg department: Department?)

    @Query("DELETE FROM department")
    fun deleteAllDepertments()
}



