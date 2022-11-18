package com.jefersonsalazar.test.data.source

import arrow.core.Either
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.domain.entities.ResponseDetailCitySearchDomain

interface RemoteCitiesDataSource {
    suspend fun searchCities(apiKey: String, search: String): Either<ErrorDomain, List<CityDomain>>
    suspend fun getDetailCity(
        apiKey: String,
        nameCity: String
    ): Either<ErrorDomain, ResponseDetailCitySearchDomain>
}