package com.greenlight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greenlight.data.local.entities.Country
import com.greenlight.data.remote.model.ApiResponse
import com.greenlight.data.repository.Repository

class MainActivityViewModel(private val repo: Repository) : ViewModel() {

    var remoteRecords: LiveData<ApiResponse>? = null
    var countries: List<Country>? = null

    fun getRemoteRecord(): LiveData<ApiResponse> {
        if (remoteRecords == null) {
            remoteRecords = repo.getRemoteRecord()
        }
        return remoteRecords!!
    }

    suspend fun getCountry(): List<Country> {
        if (countries == null) {
            try {
                countries = repo.getCountry()
            } catch (e: Exception) {
                println(e)
            }
        }
        return countries!!
    }


}