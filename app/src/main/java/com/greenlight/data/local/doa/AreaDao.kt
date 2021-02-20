package com.greenlight.data.local.doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greenlight.data.local.entities.Area


@Dao
interface AreaDao {

    @Query("SELECT * FROM Area where territory like :region and area <> 'NA' ORDER BY id desc")
    fun getAllAreas(region: String): LiveData<List<Area>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAreas(areas: List<Area>)

}