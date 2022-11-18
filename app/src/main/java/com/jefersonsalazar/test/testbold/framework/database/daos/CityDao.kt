package com.jefersonsalazar.test.testbold.framework.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jefersonsalazar.test.testbold.framework.database.entities.CityEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCity(product: CityEntity)
}