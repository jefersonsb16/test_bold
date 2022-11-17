package com.jefersonsalazar.test.testbold.framework.server.mappers

import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.testbold.framework.server.response.CityServer

fun CityServer.toCityDomain() = CityDomain(
    id, name, region, country
)