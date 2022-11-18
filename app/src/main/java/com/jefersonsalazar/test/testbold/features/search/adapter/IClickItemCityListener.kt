package com.jefersonsalazar.test.testbold.features.search.adapter

import com.jefersonsalazar.test.domain.entities.CityDomain

interface IClickItemCityListener {
    fun onItemClickListener(city: CityDomain, showBtnRemoveCity: Boolean)
}