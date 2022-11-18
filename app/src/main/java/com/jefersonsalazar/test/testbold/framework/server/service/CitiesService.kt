package com.jefersonsalazar.test.testbold.framework.server.service

import com.jefersonsalazar.test.testbold.framework.server.response.CityServer
import com.jefersonsalazar.test.testbold.framework.server.response.ResponseDetailCitySearchServer
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.API_KEY
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.DETAIL_CITY_SEARCH
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.FORECAST_DAYS
import com.jefersonsalazar.test.testbold.framework.util.ApiConstants.FORECAST_DAYS_VALUE
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

    @GET(DETAIL_CITY_SEARCH)
    suspend fun getDetailCity(
        @Query(API_KEY) apiKey: String,
        @Query(SEARCH) nameCity: String,
        @Query(FORECAST_DAYS) forecastDays: Int = FORECAST_DAYS_VALUE
    ): ResponseDetailCitySearchServer
}