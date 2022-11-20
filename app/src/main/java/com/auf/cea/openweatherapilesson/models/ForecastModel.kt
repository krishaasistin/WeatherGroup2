package com.auf.cea.openweatherapilesson.models

import java.io.Serializable

data class ForecastModel(
    var clouds: Clouds,
    var dt: Long,
    var dt_txt: String,
    var main: Main,
    var pop: Double,
    var sys: Sys,
    var visibility: Int,
    var weather: List<Weather>,
    var wind: Wind
) : Serializable