package com.jefersonsalazar.test.testbold.features.search

import androidx.lifecycle.ViewModel
import com.jefersonsalazar.test.usecases.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : ViewModel() {
}