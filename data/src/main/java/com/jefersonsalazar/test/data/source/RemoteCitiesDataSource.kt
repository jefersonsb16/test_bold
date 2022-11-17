package com.jefersonsalazar.test.data.source

import arrow.core.Either
import com.jefersonsalazar.test.domain.Error
import com.jefersonsalazar.test.domain.entities.CityDomain

interface RemoteCitiesDataSource {
    suspend fun searchCities(search: String): Either<Error, List<CityDomain>>
}