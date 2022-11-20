package com.auf.cea.openweatherapilesson.models

import java.io.Serializable

data class LocationModel(
    var location: String,
    var lat: Double,
    var lon: Double
): Serializable