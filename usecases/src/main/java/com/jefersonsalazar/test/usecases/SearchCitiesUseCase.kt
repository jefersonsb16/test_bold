package com.jefersonsalazar.test.usecases

import arrow.core.Either
import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain

class SearchCitiesUseCase(
    private val citiesRepository: CitiesRepository
) {
    suspend operator fun invoke(search: String): Either<ErrorDomain, List<CityDomain>> =
        citiesRepository.searchCities(search)
}