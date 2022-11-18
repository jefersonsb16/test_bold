package com.jefersonsalazar.test.testbold.features.detail_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.domain.entities.ResponseDetailCitySearchDomain
import com.jefersonsalazar.test.usecases.GetDetailCitySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSearchViewModel @Inject constructor(
    private val getDetailCitySearchUseCase: GetDetailCitySearchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> get() = _state.asStateFlow()

    fun onGetDetailCitySearch(nameCity: String) = viewModelScope.launch {
        showLoading()
        val response = getDetailCitySearchUseCase(nameCity)
        response.fold(
            ifLeft = { error ->
                _state.update { UIState(error = error) }
            },
            ifRight = { detailCity ->
                _state.update { it.copy(loading = false, detailCity = detailCity) }
            }
        )
    }

    private fun showLoading() {
        _state.update { it.copy(loading = true) }
    }

    fun resetState() {
        _state.update { UIState() }
    }

    data class UIState(
        val error: ErrorDomain? = null,
        val loading: Boolean = false,
        val detailCity: ResponseDetailCitySearchDomain? = null
    )
}