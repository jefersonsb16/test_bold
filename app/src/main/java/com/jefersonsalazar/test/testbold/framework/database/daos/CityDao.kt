package com.jefersonsalazar.test.testbold.framework.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jefersonsalazar.test.testbold.framework.database.entities.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCity(product: CityEntity)

    @Query("SELECT * FROM City")
    fun getRecentSearches(): Flow<List<CityEntity>>
}