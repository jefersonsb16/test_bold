package com.jefersonsalazar.test.testbold.framework.server.mappers

import com.jefersonsalazar.test.domain.entities.*
import com.jefersonsalazar.test.testbold.framework.server.response.*

fun CityServer.toCityDomain() = CityDomain(
    id, name, region, country
)

fun ResponseDetailCitySearchServer.toResponseDetailCitySearchDomain() =
    ResponseDetailCitySearchDomain(
        location = location.toLocationDomain(),
        currentWeather = currentWeather.toCurrentWeatherDomain(),
        weatherForecast = weatherForecast.toWeatherForecastDomain()
    )

fun LocationServer.toLocationDomain() = LocationDomain(
    name, region, country
)

fun CurrentWeatherServer.toCurrentWeatherDomain() = CurrentWeatherDomain(
    tempCelsius,
    tempFahrenheit,
    condition = condition.toConditionDomain()
)

fun ConditionServer.toConditionDomain() = ConditionDomain(
    description, icon
)

fun WeatherForecastServer.toWeatherForecastDomain() = WeatherForecastDomain(
    forecastDay = forecastDay.map { it.toForecastDayDomain() }
)

fun ForecastDayServer.toForecastDayDomain() = ForecastDayDomain(
    date,
    id,
    dayInfo = dayInfo.toDayInfoDomain()
)

fun DayInfoServer.toDayInfoDomain() = DayInfoDomain(
    maxTempCelsius,
    maxTempFahrenheit,
    minTempCelsius,
    minTempFahrenheit,
    condition = condition.toConditionDomain()
)