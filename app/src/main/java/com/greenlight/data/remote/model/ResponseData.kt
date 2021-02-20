package com.greenlight.data.remote.model

import com.greenlight.data.local.entities.*

data class ResponseData(
    val area: List<Area>,
    val country: List<Country>,
    val employee: List<Employee>,
    val region: List<Region>,
    val zone: List<Zone>
)