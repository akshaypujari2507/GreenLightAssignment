package com.greenlight.data.remote.api

import com.greenlight.data.remote.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("assignment")
    fun getResponse(): Call<ApiResponse>

}