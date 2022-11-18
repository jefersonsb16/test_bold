package com.jefersonsalazar.test.data.repository

import arrow.core.Either
import com.jefersonsalazar.test.data.source.LocalCitiesDataSource
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.domain.entities.ResponseDetailCitySearchDomain
import kotlinx.coroutines.flow.Flow

class CitiesRepository(
    private val apiKey: String,
    private val localCitiesDataSource: LocalCitiesDataSource,
    private val remoteCitiesDataSource: RemoteCitiesDataSource
) {

    suspend fun searchCities(search: String): Either<ErrorDomain, List<CityDomain>> =
        remoteCitiesDataSource.searchCities(apiKey, search)

    suspend fun saveCity(city: CityDomain) = localCitiesDataSource.saveCity(city)

    fun getRecentSearches(): Flow<List<CityDomain>> = localCitiesDataSource.getRecentSearches()

    suspend fun getDetailCity(nameCity: String): Either<ErrorDomain, ResponseDetailCitySearchDomain> =
        remoteCitiesDataSource.getDetailCity(apiKey, nameCity)
}