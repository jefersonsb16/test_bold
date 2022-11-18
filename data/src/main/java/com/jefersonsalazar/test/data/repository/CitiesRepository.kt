package com.jefersonsalazar.test.data.repository

import arrow.core.Either
import com.jefersonsalazar.test.data.source.LocalCitiesDataSource
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain

class CitiesRepository(
    private val apiKey: String,
    private val localCitiesDataSource: LocalCitiesDataSource,
    private val remoteCitiesDataSource: RemoteCitiesDataSource
) {

    suspend fun searchCities(search: String): Either<ErrorDomain, List<CityDomain>> =
        remoteCitiesDataSource.searchCities(apiKey, search)

    suspend fun saveCity(city: CityDomain) = localCitiesDataSource.saveCity(city)
}