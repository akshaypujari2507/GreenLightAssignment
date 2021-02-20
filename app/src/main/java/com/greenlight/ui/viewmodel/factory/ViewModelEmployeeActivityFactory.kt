package com.greenlight.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenlight.data.repository.Repository
import com.greenlight.ui.viewmodel.EmployeeViewModel

class ViewModelEmployeeActivityFactory(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}