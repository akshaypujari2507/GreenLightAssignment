package com.greenlight.data.local.doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greenlight.data.local.entities.Zone


@Dao
interface ZoneDao {

    @Query("SELECT * FROM Zone where territory like :country and zone <> 'NA' ORDER BY id desc")
    fun getAllZones(country: String): LiveData<List<Zone>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertZones(zones: List<Zone>)

}