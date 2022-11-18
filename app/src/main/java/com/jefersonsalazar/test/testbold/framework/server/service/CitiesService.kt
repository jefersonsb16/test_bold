package com.jefersonsalazar.test.testbold.framework.server.service

import com.jefersonsalazar.test.testbold.framework.server.response.CityServer
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.API_KEY
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.SEARCH
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.SEARCH_CITY
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesService {

    @GET(SEARCH_CITY)
    suspend fun searchCities(
        @Query(API_KEY) apiKey: String,
        @Query(SEARCH) search: String
    ): List<CityServer>
}