package com.jefersonsalazar.test.testbold.framework.server.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailCitySearchServer(
    val location: LocationServer,
    @SerializedName("current")
    val currentWeather: CurrentWeatherServer,
    @SerializedName("forecast")
    val weatherForecast: WeatherForecastServer
)

data class LocationServer(
    val name: String = "",
    val region: String = "",
    val country: String = ""
)

data class CurrentWeatherServer(
    @SerializedName("temp_c")
    val tempCelsius: Double = 0.0,
    @SerializedName("temp_f")
    val tempFahrenheit: Double = 0.0,
    val condition: ConditionServer
)

data class ConditionServer(
    @SerializedName("text")
    val description: String = "",
    val icon: String = ""
)

data class WeatherForecastServer(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDayServer>
)

data class ForecastDayServer(
    val date: String = "",
    @SerializedName("day")
    val dayInfo: DayInfoServer
)

data class DayInfoServer(
    @SerializedName("maxtemp_c")
    val maxTempCelsius: Double = 0.0,
    @SerializedName("maxtemp_f")
    val maxTempFahrenheit: Double = 0.0,
    @SerializedName("mintemp_c")
    val minTempCelsius: Double = 0.0,
    @SerializedName("mintemp_f")
    val minTempFahrenheit: Double = 0.0,
    val condition: ConditionServer
)