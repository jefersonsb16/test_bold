package com.jefersonsalazar.test.data.source

import com.jefersonsalazar.test.domain.entities.CityDomain
import kotlinx.coroutines.flow.Flow

interface LocalCitiesDataSource {
    suspend fun saveCity(city: CityDomain)
    fun getRecentSearches(): Flow<List<CityDomain>>
}