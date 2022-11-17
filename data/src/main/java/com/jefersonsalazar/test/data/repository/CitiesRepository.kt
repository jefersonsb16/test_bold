package com.jefersonsalazar.test.data.repository

import arrow.core.Either
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.domain.Error
import com.jefersonsalazar.test.domain.entities.CityDomain

class CitiesRepository(
    private val remoteCitiesDataSource: RemoteCitiesDataSource
) {

    suspend fun searchCities(search: String): Either<Error, List<CityDomain>> =
        remoteCitiesDataSource.searchCities(search)
}