package com.jefersonsalazar.test.usecases

import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.domain.entities.CityDomain

class SaveRecentCityViewedUseCase(
    private val citiesRepository: CitiesRepository
) {

    suspend operator fun invoke(city: CityDomain) =
        citiesRepository.saveCity(city)
}