package com.greenlight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greenlight.data.local.entities.Area
import com.greenlight.data.repository.Repository

class AreaViewModel(private val repo: Repository) : ViewModel() {

    var areas: LiveData<List<Area>>? = null

    suspend fun getAreas(region: String): LiveData<List<Area>> {
        if (areas == null) {
            try {
                areas = repo.getAreas(region)
            } catch (e: Exception) {
                println(e)
            }
        }
        return areas!!
    }


}