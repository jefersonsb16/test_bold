package com.jefersonsalazar.test.usecases

import arrow.core.Either
import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.domain.CityDomain
import com.jefersonsalazar.test.domain.Error

class SearchCitiesUseCase(
    private val citiesRepository: CitiesRepository
) {
    suspend operator fun invoke(search: String): Either<Error, List<CityDomain>> =
        citiesRepository.searchCities(search)
}