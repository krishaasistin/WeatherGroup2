package com.auf.cea.openweatherapilesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auf.cea.openweatherapilesson.databinding.ActivityViewMoreBinding
import com.auf.cea.openweatherapilesson.models.ForecastModel

class ViewMore : AppCompatActivity() {
    private lateinit var binding: ActivityViewMoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weather = intent.getSerializableExtra("weatherData") as ForecastModel

        val feelsLike = weather.main.feels_like // Feels Like
        val minTemp = weather.main.temp_min // Min Temp
        val maxTemp = weather.main.temp_max // Max Temp
        val rainChance = weather.pop // Chance of Rain
        val cloudiness = weather.clouds.all // Cloudiness
        val humidity = weather.main.humidity // Humidity
        val pressure = weather.main.pressure // Pressure
        val gndLevel = weather.main.grnd_level // Ground Level Pressure
        val seaLevel = weather.main.sea_level // Sea Level Pressure
        val windSpeed = weather.wind.speed // Wind Speed
        val visibility = weather.visibility
        val windGust = weather.wind.gust // Wind Gust



        binding.txtFeelsLike.text = String.format("\uD83C\uDF21\n%s°C", feelsLike)
        binding.txtMinTemp.text = String.format("⬇️\n%s°C", minTemp)
        binding.txtMaxTemp.text = String.format("⬆️\n%s°C", maxTemp)
        binding.txtRainChance.text = String.format(" \uD83C\uDF27\n%s%%", rainChance)
        binding.txtCloudiness.text = String.format(" ☁\n%s%%", cloudiness)
        binding.txtHumidity.text = String.format(" \uD83D\uDCA7\n%s%%", humidity)
        binding.txtPressure.text = String.format(" \uD83D\uDCA8\n%shPa", pressure)
        binding.txtGndLevel.text = String.format(" \uD83C\uDFD4\n%shPa", gndLevel)
        binding.txtSeaLevel.text = String.format(" \uD83C\uDF0A\n%shPa", seaLevel)
        binding.txtWindSpeed.text = String.format(" \uD83C\uDF2C\n%sm/s", windSpeed)
        binding.txtVisibility.text = String.format("\uD83D\uDC41\n%sm", visibility)
        binding.txtWindGust.text = String.format("\uD83C\uDF00\n%sm/s", windGust)




    }
}