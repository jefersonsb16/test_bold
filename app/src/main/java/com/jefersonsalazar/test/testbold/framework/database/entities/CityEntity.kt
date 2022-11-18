package com.jefersonsalazar.test.testbold.framework.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "City")
data class CityEntity(
    @PrimaryKey
    val id: Long,
    val name: String = "",
    val region: String = "",
    val country: String = ""
)