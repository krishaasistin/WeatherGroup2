package com.auf.cea.openweatherapilesson.models

import java.io.Serializable

data class MainForecastModel(
    var cnt: Int,
    var cod: String,
    var message: Int,
    var city: CityModel,
    var list: List<ForecastModel>
): Serializable