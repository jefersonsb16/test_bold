package com.jefersonsalazar.test.testbold

import app.cash.turbine.test
import arrow.core.right
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.testbold.features.search.SearchCityViewModel
import com.jefersonsalazar.test.testbold.features.search.SearchCityViewModel.UIState
import com.jefersonsalazar.test.usecases.GetLocalRecentSearchesUseCase
import com.jefersonsalazar.test.usecases.SaveRecentCityViewedUseCase
import com.jefersonsalazar.test.usecases.SearchCitiesUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchCityViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var searchCitiesUseCase: SearchCitiesUseCase

    @Mock
    lateinit var saveRecentCityViewedUseCase: SaveRecentCityViewedUseCase

    @Mock
    lateinit var getLocalRecentSearchesUseCase: GetLocalRecentSearchesUseCase

    private lateinit var searchCityViewModel: SearchCityViewModel

    @Before
    fun setUp() {
        searchCityViewModel = SearchCityViewModel(
            searchCitiesUseCase, saveRecentCityViewedUseCase, getLocalRecentSearchesUseCase
        )
    }

    @Test
    fun `State is updated when calls onSearchCity`() = runTest {
        val mockCities = listOf(mockedCity.copy(id = 123))
        whenever(searchCitiesUseCase("")).thenReturn(mockCities.right())
        searchCityViewModel.onSearchCity("")

        searchCityViewModel.state.test {
            assertEquals(UIState(), awaitItem())
            assertEquals(UIState(loading = true), awaitItem())
            assertEquals(
                UIState(
                    searchResultsList = mockCities, loading = false, showMessageStartSearch = false,
                    showMessageNotSearchResults = mockCities.isEmpty()
                ), awaitItem()
            )
            cancel()
        }
    }

    @Test
    fun `Invoke saveCityInRecentlyViewed`() = runTest {
        val mockCity = mockedCity.copy(id = 123)
        searchCityViewModel.saveCityInRecentlyViewed(mockCity)
        runCurrent()

        verify(saveRecentCityViewedUseCase).invoke(mockCity)
    }
}

val mockedCity = CityDomain(
    0
)