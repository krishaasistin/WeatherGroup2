package com.auf.cea.openweatherapilesson.models

import java.io.Serializable

data class CityModel(
    var coord: Coord,
    var country: String,
    var id: Int,
    var name: String,
    var population: Int,
    var sunrise: Int,
    var sunset: Int,
    var timezone: Int
): Serializable