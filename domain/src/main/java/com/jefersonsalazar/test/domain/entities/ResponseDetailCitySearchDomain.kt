package com.jefersonsalazar.test.domain.entities

data class ResponseDetailCitySearchDomain(
    val location: LocationDomain,
    val currentWeather: CurrentWeatherDomain,
    val weatherForecast: WeatherForecastDomain
)

data class LocationDomain(
    val name: String = "",
    val region: String = "",
    val country: String = ""
)

data class CurrentWeatherDomain(
    val tempCelsius: Double = 0.0,
    val tempFahrenheit: Double = 0.0,
    val condition: ConditionDomain
)

data class ConditionDomain(
    val description: String = "",
    val icon: String = ""
)

data class WeatherForecastDomain(
    val forecastDay: List<ForecastDayDomain>
)

data class ForecastDayDomain(
    val date: String = "",
    val id: Long = 0,
    val dayInfo: DayInfoDomain
)

data class DayInfoDomain(
    val maxTempCelsius: Double = 0.0,
    val maxTempFahrenheit: Double = 0.0,
    val minTempCelsius: Double = 0.0,
    val minTempFahrenheit: Double = 0.0,
    val condition: ConditionDomain
)