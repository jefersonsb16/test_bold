package com.jefersonsalazar.test.testbold.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jefersonsalazar.test.testbold.framework.database.daos.CityDao
import com.jefersonsalazar.test.testbold.framework.database.entities.CityEntity

@Database(
    entities = [
        CityEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BoldDB : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        @Synchronized
        fun getDatabase(context: Context): BoldDB = Room.databaseBuilder(
            context.applicationContext,
            BoldDB::class.java,
            "bold_db"
        ).build()
    }
}