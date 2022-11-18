package com.jefersonsalazar.test.data.source

import arrow.core.Either
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain

interface RemoteCitiesDataSource {
    suspend fun searchCities(apiKey: String, search: String): Either<ErrorDomain, List<CityDomain>>
}