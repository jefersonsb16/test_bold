package com.jefersonsalazar.test.testbold.di

import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.data.source.LocalCitiesDataSource
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.testbold.framework.server.service.CitiesService
import com.jefersonsalazar.test.testbold.framework.server.source.LocalCitiesDataSourceImpl
import com.jefersonsalazar.test.testbold.framework.server.source.RemoteCitiesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun localCitiesDataSourceProvider(): LocalCitiesDataSource = LocalCitiesDataSourceImpl()

    @Provides
    fun remoteCitiesDataSourceProvider(
        citiesService: CitiesService
    ): RemoteCitiesDataSource = RemoteCitiesDataSourceImpl(citiesService)

    @Provides
    fun citiesRepositoryProvider(
        remoteCitiesDataSource: RemoteCitiesDataSource
    ) = CitiesRepository(remoteCitiesDataSource)
}