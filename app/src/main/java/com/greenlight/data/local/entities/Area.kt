package com.greenlight.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Area", indices = [Index(value = ["area"], unique = true)])
class Area {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var area: String? = null
    var territory: String? = null

    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id!!
    }

    override fun toString(): String {
        return "Area(Name=$area, territory=$territory)"
    }
}