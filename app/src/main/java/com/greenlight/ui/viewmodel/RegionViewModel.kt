package com.greenlight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greenlight.data.local.entities.Region
import com.greenlight.data.repository.Repository

class RegionViewModel(private val repo: Repository) : ViewModel() {

    var regions: LiveData<List<Region>>? = null

    suspend fun getRegions(zone: String): LiveData<List<Region>> {
        if (regions == null) {
            try {
                regions = repo.getRegions(zone)
            } catch (e: Exception) {
                println(e)
            }
        }
        return regions!!
    }

}