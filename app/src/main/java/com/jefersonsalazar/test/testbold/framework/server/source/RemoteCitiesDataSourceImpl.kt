package com.jefersonsalazar.test.testbold.framework.server.source

import arrow.core.Either
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.domain.entities.ResponseDetailCitySearchDomain
import com.jefersonsalazar.test.testbold.framework.server.mappers.toCityDomain
import com.jefersonsalazar.test.testbold.framework.server.mappers.toResponseDetailCitySearchDomain
import com.jefersonsalazar.test.testbold.framework.server.service.CitiesService
import com.jefersonsalazar.test.testbold.framework.util.tryCall

class RemoteCitiesDataSourceImpl(
    private val citiesService: CitiesService
) : RemoteCitiesDataSource {
    override suspend fun searchCities(
        apiKey: String,
        search: String
    ): Either<ErrorDomain, List<CityDomain>> = tryCall {
        citiesService.searchCities(apiKey, search).map { it.toCityDomain() }
    }

    override suspend fun getDetailCity(
        apiKey: String,
        nameCity: String
    ): Either<ErrorDomain, ResponseDetailCitySearchDomain> = tryCall {
        citiesService.getDetailCity(apiKey, nameCity).toResponseDetailCitySearchDomain()
    }
}