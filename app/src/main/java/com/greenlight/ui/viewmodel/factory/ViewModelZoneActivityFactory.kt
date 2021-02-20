package com.greenlight.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenlight.data.repository.Repository
import com.greenlight.ui.viewmodel.ZoneViewModel

class ViewModelZoneActivityFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZoneViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ZoneViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}