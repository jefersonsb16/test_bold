package com.jefersonsalazar.test.testbold.framework.server.source

import com.jefersonsalazar.test.data.source.LocalCitiesDataSource
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.testbold.framework.database.BoldDB
import com.jefersonsalazar.test.testbold.framework.database.mappers.toCityEntity

class LocalCitiesDataSourceImpl(
    private val db: BoldDB
) : LocalCitiesDataSource {

    private val cityDao by lazy { db.cityDao() }

    override suspend fun saveCity(city: CityDomain) {
        cityDao.saveCity(city.toCityEntity())
    }
}