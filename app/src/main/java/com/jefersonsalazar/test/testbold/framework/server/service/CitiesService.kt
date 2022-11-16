package com.jefersonsalazar.test.testbold.framework.server.service

import com.jefersonsalazar.test.testbold.framework.server.response.CityServer
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.SEARCH_CITY
import retrofit2.http.GET

interface CitiesService {

    @GET(SEARCH_CITY)
    fun searchCities(search: String): List<CityServer>
}