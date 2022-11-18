package com.jefersonsalazar.test.data

import arrow.core.right
import com.jefersonsalazar.test.data.repository.CitiesRepository
import com.jefersonsalazar.test.data.source.LocalCitiesDataSource
import com.jefersonsalazar.test.data.source.RemoteCitiesDataSource
import com.jefersonsalazar.test.domain.entities.CityDomain
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CitiesRepositoryTest {

    private var apiKey: String = "abd12Sad4dSab234"

    @Mock
    lateinit var localCitiesDataSource: LocalCitiesDataSource

    @Mock
    lateinit var remoteCitiesDataSource: RemoteCitiesDataSource

    lateinit var citiesRepository: CitiesRepository

    @Before
    fun setUp() {
        citiesRepository = CitiesRepository(
            apiKey,
            localCitiesDataSource,
            remoteCitiesDataSource
        )
    }

    @Test
    fun `getRecentCities from local data source`() = runTest {
        val recentCities = flowOf(listOf(mockedCity.copy(id = 1)))
        whenever(localCitiesDataSource.getRecentSearches()).thenReturn(
            recentCities
        )
        val result = citiesRepository.getRecentSearches()
        assertEquals(recentCities, result)
    }

    @Test
    fun `searchCities from remote data source`() = runTest {
        val remoteCities = listOf(mockedCity.copy(id = 1)).right()
        whenever(remoteCitiesDataSource.searchCities(apiKey, "")).thenReturn(
            remoteCities
        )
        val result = citiesRepository.searchCities("")
        assertEquals(remoteCities, result)
    }

    @Test
    fun `verify invoke saveCity and validate saved object`() = runTest {
        val city = mockedCity.copy()
        citiesRepository.saveCity(city)

        verify(localCitiesDataSource).saveCity(argThat {
            this.name == city.name
        })
    }
}

val mockedCity = CityDomain(
    0
)