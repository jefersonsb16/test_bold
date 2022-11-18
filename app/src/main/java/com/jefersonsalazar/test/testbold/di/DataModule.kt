package com.jefersonsalazar.test.testbold.di

import android.app.Application
import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.data.source.LocalCitiesDataSource
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.testbold.framework.database.BoldDB
import com.jefersonsalazar.test.testbold.framework.server.service.CitiesService
import com.jefersonsalazar.test.testbold.framework.server.source.LocalCitiesDataSourceImpl
import com.jefersonsalazar.test.testbold.framework.server.source.RemoteCitiesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): BoldDB = BoldDB.getDatabase(app)

    @Provides
    fun localCitiesDataSourceProvider(
        db: BoldDB
    ): LocalCitiesDataSource = LocalCitiesDataSourceImpl(db)

    @Provides
    fun remoteCitiesDataSourceProvider(
        citiesService: CitiesService
    ): RemoteCitiesDataSource = RemoteCitiesDataSourceImpl(citiesService)

    @Provides
    fun citiesRepositoryProvider(
        @Named("apiKey") apiKey: String,
        localCitiesDataSource: LocalCitiesDataSource,
        remoteCitiesDataSource: RemoteCitiesDataSource
    ) = CitiesRepository(
        apiKey,
        localCitiesDataSource,
        remoteCitiesDataSource
    )
}