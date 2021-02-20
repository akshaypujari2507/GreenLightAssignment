package com.greenlight.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Region", indices = [Index(value = ["region"], unique = true)])
class Region {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var region: String? = null
    var territory: String? = null

    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id!!
    }

    override fun toString(): String {
        return "Region(Name=$region, territory=$territory)"
    }
}