package com.greenlight.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Zone", indices = [Index(value = ["zone"], unique = true)])
class Zone {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var zone: String? = null
    var territory: String? = null

    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id!!
    }

    override fun toString(): String {
        return "Zone(Name=$zone, territory=$territory)"
    }
}