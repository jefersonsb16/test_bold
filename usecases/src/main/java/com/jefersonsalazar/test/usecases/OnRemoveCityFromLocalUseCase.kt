package com.jefersonsalazar.test.usecases

import com.jefersonsalazar.test.data.repository.CitiesRepository

class OnRemoveCityFromLocalUseCase(
    private val citiesRepository: CitiesRepository
) {
    suspend operator fun invoke(id: Long) = citiesRepository.removeCity(id)
}