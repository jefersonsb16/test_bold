package com.jefersonsalazar.test.testbold.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.usecases.SaveRecentCityViewedUseCase
import com.jefersonsalazar.test.usecases.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val saveRecentCityViewedUseCase: SaveRecentCityViewedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> get() = _state.asStateFlow()

    fun onSearchCity(search: String) = viewModelScope.launch {
        showLoading()
        val responseSearch = searchCitiesUseCase(search)
        responseSearch.fold(
            ifLeft = { error ->
                _state.update { UIState(error = error) }
            },
            ifRight = { results ->
                _state.update { it.copy(searchResultsList = results, loading = false) }
            }
        )
    }

    private fun showLoading() {
        _state.update { it.copy(loading = true) }
    }

    fun saveCityInRecentlyViewed(city: CityDomain) = viewModelScope.launch {
        saveRecentCityViewedUseCase(city)
    }

    data class UIState(
        val error: ErrorDomain? = null,
        val loading: Boolean = false,
        val searchResultsList: List<CityDomain>? = null,
        val recentSearchesList: List<CityDomain>? = null
    )
}