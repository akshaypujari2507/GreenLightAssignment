package com.greenlight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greenlight.data.local.entities.Employee
import com.greenlight.data.repository.Repository

class EmployeeViewModel(private val repo: Repository) : ViewModel() {

    var employees: LiveData<List<Employee>>? = null

    suspend fun getEmployees(area: String): LiveData<List<Employee>> {
        if (employees == null) {
            try {
                employees = repo.getEmployees(area)
            } catch (e: Exception) {
                println(e)
            }
        }
        return employees!!
    }


}