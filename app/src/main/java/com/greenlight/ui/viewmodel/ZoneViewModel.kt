package com.greenlight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greenlight.data.local.entities.Zone
import com.greenlight.data.repository.Repository

class ZoneViewModel(private val repo: Repository) : ViewModel() {

    var zones: LiveData<List<Zone>>? = null

    suspend fun getZones(country: String): LiveData<List<Zone>> {
        if (zones == null) {
            try {
                zones = repo.getZones(country)
            } catch (e: Exception) {
                println(e)
            }
        }
        return zones!!
    }

}