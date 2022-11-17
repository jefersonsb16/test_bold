package com.jefersonsalazar.test.domain.entities

data class CityDomain(
    val id: Long,
    val name: String = "",
    val region: String = "",
    val country: String = ""
)