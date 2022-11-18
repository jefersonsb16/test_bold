package com.jefersonsalazar.test.usecases

import arrow.core.Either
import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.ResponseDetailCitySearchDomain

class GetDetailCitySearchUseCase(
    private val citiesRepository: CitiesRepository
) {
    suspend operator fun invoke(nameCity: String): Either<ErrorDomain, ResponseDetailCitySearchDomain> =
        citiesRepository.getDetailCity(nameCity)
}