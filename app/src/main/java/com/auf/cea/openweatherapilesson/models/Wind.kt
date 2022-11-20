package com.auf.cea.openweatherapilesson.models

import java.io.Serializable

data class Wind(
    var deg: Int,
    var gust: Double,
    var speed: Double
): Serializable