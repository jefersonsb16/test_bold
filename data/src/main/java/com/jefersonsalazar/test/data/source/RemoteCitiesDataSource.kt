package com.jefersonsalazar.test.data.source

import arrow.core.Either
import com.jefersonsalazar.test.domain.CityDomain
import com.jefersonsalazar.test.domain.Error

interface RemoteCitiesDataSource {
    suspend fun searchCities(search: String): Either<Error, List<CityDomain>>
}