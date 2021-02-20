package com.greenlight.data.local.doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greenlight.data.local.entities.Employee


@Dao
interface EmployeeDao {

    @Query("SELECT * FROM Employee where area like :area and name <> 'NA' ORDER BY name")
    fun getAllEmployees(area: String): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(employees: List<Employee>)

}