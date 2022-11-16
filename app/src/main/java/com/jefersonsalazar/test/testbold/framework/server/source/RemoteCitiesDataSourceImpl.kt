package com.jefersonsalazar.test.testbold.framework.server.source

import arrow.core.Either
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.domain.CityDomain
import com.jefersonsalazar.test.domain.Error
import com.jefersonsalazar.test.testbold.framework.server.mappers.toCityDomain
import com.jefersonsalazar.test.testbold.framework.server.service.CitiesService
import com.jefersonsalazar.test.testbold.framework.util.tryCall

class RemoteCitiesDataSourceImpl(
    private val citiesService: CitiesService
) : RemoteCitiesDataSource {
    override suspend fun searchCities(search: String): Either<Error, List<CityDomain>> = tryCall {
        citiesService.searchCities(search).map { it.toCityDomain() }
    }
}