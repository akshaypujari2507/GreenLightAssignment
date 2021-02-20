package com.greenlight.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Country", indices = [Index(value = ["country"], unique = true)])
class Country {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var country: String? = null
    var territory: String? = null

    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id!!
    }

    override fun toString(): String {
        return "Country(Name=$country, territory=$territory)"
    }
}