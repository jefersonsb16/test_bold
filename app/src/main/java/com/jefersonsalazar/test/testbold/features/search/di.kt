package com.jefersonsalazar.test.testbold.features.search

import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.usecases.SaveRecentCityViewedUseCase
import com.jefersonsalazar.test.usecases.SearchCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SearchCityModule {

    @Provides
    @ViewModelScoped
    fun searchCitiesUseCaseProvider(
        citiesRepository: CitiesRepository
    ) = SearchCitiesUseCase(citiesRepository)

    @Provides
    @ViewModelScoped
    fun saveRecentCityViewedUseCaseProvider(
        citiesRepository: CitiesRepository
    ) = SaveRecentCityViewedUseCase(citiesRepository)
}