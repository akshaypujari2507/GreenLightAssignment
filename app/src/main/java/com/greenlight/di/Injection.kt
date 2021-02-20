package com.greenlight.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.greenlight.data.local.db.AppDatabase
import com.greenlight.data.remote.api.ApiClient
import com.greenlight.data.repository.Repository
import com.greenlight.ui.viewmodel.factory.*


object Injection {

    var repo: Repository? = null

    //repo provider
    private fun provideRepository(context: Context): Repository {
        val database = AppDatabase.getInstance(context)
        val api = ApiClient.api

        if (repo == null) {
            synchronized(Repository::class.java) {
                if (repo == null) {
                    repo = Repository(database, api)
                }
            }
        }
        return repo!!
    }

    // main activity viewmodel provider
    fun provideMainActivityViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelMainActivityFactory(
            provideRepository(
                context
            )
        )
    }

    // zone activity viewmodel provider
    fun provideZoneActivityViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelZoneActivityFactory(
            provideRepository(
                context
            )
        )
    }

    // region activity viewmodel provider
    fun provideRegionActivityViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelRegionActivityFactory(
            provideRepository(
                context
            )
        )
    }

    // area activity viewmodel provider
    fun provideAreaActivityViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelAreaActivityFactory(
            provideRepository(
                context
            )
        )
    }

    // employee activity viewmodel provider
    fun provideEmployeeActivityViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelEmployeeActivityFactory(
            provideRepository(
                context
            )
        )
    }

}