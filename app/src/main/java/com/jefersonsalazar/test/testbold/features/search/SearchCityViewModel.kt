package com.jefersonsalazar.test.testbold.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.usecases.GetLocalRecentSearchesUseCase
import com.jefersonsalazar.test.usecases.SaveRecentCityViewedUseCase
import com.jefersonsalazar.test.usecases.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val saveRecentCityViewedUseCase: SaveRecentCityViewedUseCase,
    private val getLocalRecentSearchesUseCase: GetLocalRecentSearchesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> get() = _state.asStateFlow()

    init {
        onGetRecentSearches()
    }

    fun onSearchCity(search: String) = viewModelScope.launch {
        showLoading()
        val responseSearch = searchCitiesUseCase(search)
        responseSearch.fold(
            ifLeft = { error ->
                _state.update { it.copy(error = error, loading = false) }
            },
            ifRight = { results ->
                _state.update {
                    it.copy(
                        searchResultsList = results,
                        loading = false,
                        showMessageStartSearch = false,
                        showMessageNotSearchResults = results.isEmpty()
                    )
                }
            }
        )
    }

    private fun onGetRecentSearches() = viewModelScope.launch {
        getLocalRecentSearchesUseCase()
            .catch {}
            .collect { recentCities ->
                _state.update { it.copy(recentSearchesList = recentCities) }
            }
    }

    private fun showLoading() {
        _state.update { it.copy(loading = true) }
    }

    fun saveCityInRecentlyViewed(city: CityDomain) = viewModelScope.launch {
        saveRecentCityViewedUseCase(city)
    }

    fun clearCurrentSearchResults() {
        _state.update { it.copy(searchResultsList = listOf(), showMessageStartSearch = true) }
    }

    fun resetStateError() {
        _state.update { it.copy(error = null) }
    }

    data class UIState(
        val error: ErrorDomain? = null,
        val loading: Boolean = false,
        val searchResultsList: List<CityDomain>? = null,
        val recentSearchesList: List<CityDomain>? = null,
        val showMessageStartSearch: Boolean? = null,
        val showMessageNotSearchResults: Boolean? = null
    )
}