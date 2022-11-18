package com.jefersonsalazar.test.data.source

import com.jefersonsalazar.test.domain.entities.CityDomain

interface LocalCitiesDataSource {
    suspend fun saveCity(city: CityDomain)
}