package com.greenlight.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.greenlight.data.local.db.AppDatabase
import com.greenlight.data.local.entities.*
import com.greenlight.data.remote.api.ApiService
import com.greenlight.data.remote.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(val db: AppDatabase, val api: ApiService) {

    var countries: List<Country> = emptyList()
    lateinit var zones: LiveData<List<Zone>>
    lateinit var regions: LiveData<List<Region>>
    lateinit var areas: LiveData<List<Area>>
    lateinit var employees: LiveData<List<Employee>>

    fun getRemoteRecord(): LiveData<ApiResponse> {

        val remoteRecords: MutableLiveData<ApiResponse> = MutableLiveData<ApiResponse>()

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getResponse().execute().body()
            remoteRecords.postValue(response)
            insertRecords(response!!)
        }

        return remoteRecords
    }

    fun insertRecords(response: ApiResponse) {
        val responseData = response.ResponseData

        val country = responseData.country
        val zone = responseData.zone
        val region = responseData.region
        val area = responseData.area
        val employee = responseData.employee

        GlobalScope.launch(Dispatchers.IO) {
            db.countryDao().insertCountries(country)
            db.zoneDao().insertZones(zone)
            db.regionDao().insertRegions(region)
            db.areaDao().insertAreas(area)
            db.employeeDao().insertEmployees(employee)
        }

    }

    suspend fun getCountry(): List<Country> {
        withContext(Dispatchers.IO) {
            try {
                countries = db.countryDao().getAllCountry()
            } catch (e: Exception) {
                println(e)
            }
        }
        return countries
    }

    suspend fun getZones(country: String): LiveData<List<Zone>> {

        withContext(Dispatchers.IO) {
            try {
                zones = db.zoneDao().getAllZones(country)
            } catch (e: Exception) {
                println(e)
            }
        }
        return zones
    }

    suspend fun getRegions(zone: String): LiveData<List<Region>> {

        withContext(Dispatchers.IO) {
            try {
                regions = db.regionDao().getAllRegions(zone)
            } catch (e: Exception) {
                println(e)
            }
        }
        return regions
    }

    suspend fun getAreas(region: String): LiveData<List<Area>> {

        withContext(Dispatchers.IO) {
            try {
                areas = db.areaDao().getAllAreas(region)
            } catch (e: Exception) {
                println(e)
            }
        }
        return areas
    }

    suspend fun getEmployees(area: String): LiveData<List<Employee>> {

        withContext(Dispatchers.IO) {
            try {
                employees = db.employeeDao().getAllEmployees(area)
            } catch (e: Exception) {
                println(e)
            }
        }
        return employees
    }

}