package com.auf.cea.openweatherapilesson.models

import java.io.Serializable

data class Weather(
    var description: String,
    var icon: String,
    var id: Int,
    var main: String
): Serializable