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

        val rainChance = weather.pop // Chance of Rain
        val cloudiness = weather.clouds.all // Cloudiness
        val windSpeed = weather.wind.speed // Wind Speed
        val pressure = weather.main.pressure // Pressure
        val gndLevel = weather.main.grnd_level // Ground Level Pressure
        val seaLevel = weather.main.sea_level // Sea Level Pressure

        binding.txtRainChance.text = String.format("Rain Chance \n%s%%", rainChance)
        binding.txtCloudiness.text = String.format("Cloudiness \n%s%%", cloudiness)
        binding.txtWindSpeed.text = String.format("Wind Speed \n%s%%", windSpeed)
        binding.txtPressure.text = String.format("Pressure \n%shPa", pressure)
        binding.txtGndLevel.text = String.format("Ground Level Pressure \n%shPa", gndLevel)
        binding.txtSeaLevel.text = String.format(" Sea Level Pressure \n%shPa", seaLevel)

    }
}