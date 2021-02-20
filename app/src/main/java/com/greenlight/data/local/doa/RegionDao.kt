package com.greenlight.data.local.doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greenlight.data.local.entities.Region


@Dao
interface RegionDao {

    @Query("SELECT * FROM Region where region like :zone and region <> 'NA' ORDER BY id desc")
    fun getAllRegions(zone: String): LiveData<List<Region>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegions(regions: List<Region>)

}