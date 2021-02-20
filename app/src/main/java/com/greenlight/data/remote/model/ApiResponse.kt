package com.greenlight.data.remote.model

data class ApiResponse(
    val ResponseData: ResponseData,
    val ResponseStatus: Int,
    val Success: Boolean
)