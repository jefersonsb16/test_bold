package com.jefersonsalazar.test.testbold.framework.database.mappers

import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.testbold.framework.database.entities.CityEntity

fun CityDomain.toCityEntity() = CityEntity(
    id, name, region, country
)