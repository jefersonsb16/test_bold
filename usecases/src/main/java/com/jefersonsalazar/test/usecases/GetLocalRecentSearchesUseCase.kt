package com.jefersonsalazar.test.usecases

import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.domain.entities.CityDomain
import kotlinx.coroutines.flow.Flow

class GetLocalRecentSearchesUseCase(
    private val citiesRepository: CitiesRepository
) {
    operator fun invoke(): Flow<List<CityDomain>> = citiesRepository.getRecentSearches()
}