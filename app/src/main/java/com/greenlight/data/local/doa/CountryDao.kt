package com.greenlight.data.local.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greenlight.data.local.entities.Country


@Dao
interface CountryDao {

    @Query("SELECT * FROM Country where country <> 'NA' ORDER BY id desc")
    fun getAllCountry(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<Country>)

}