package com.jefersonsalazar.test.testbold.features.detail_search

import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.usecases.GetDetailCitySearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailCitySearchModule {

    @Provides
    @ViewModelScoped
    fun getDetailCitySearchUseCaseProvider(
        citiesRepository: CitiesRepository
    ) = GetDetailCitySearchUseCase(citiesRepository)
}