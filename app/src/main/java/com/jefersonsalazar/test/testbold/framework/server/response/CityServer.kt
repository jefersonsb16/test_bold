package com.jefersonsalazar.test.testbold.framework.server.response

data class CityServer(
    val id: Long,
    val name: String = "",
    val region: String = "",
    val country: String = ""
)