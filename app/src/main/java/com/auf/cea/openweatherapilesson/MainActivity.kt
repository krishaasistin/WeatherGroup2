package com.auf.cea.openweatherapilesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auf.cea.openweatherapilesson.adapter.WeatherAdapter
import com.auf.cea.openweatherapilesson.constants.API_KEY
import com.auf.cea.openweatherapilesson.databinding.ActivityMainBinding
import com.auf.cea.openweatherapilesson.models.ForecastModel
import com.auf.cea.openweatherapilesson.models.LocationModel
import com.auf.cea.openweatherapilesson.services.helper.RetrofitHelper
import com.auf.cea.openweatherapilesson.services.repository.OpenWeatherAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WeatherAdapter
    private lateinit var forecastData: ArrayList<ForecastModel>
    private lateinit var locationList: ArrayList<LocationModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        forecastData = arrayListOf()
        adapter = WeatherAdapter(forecastData,this)

        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvForecast.layoutManager = layoutManager
        binding.rvForecast.adapter = adapter

        locationList = arrayListOf(
            LocationModel("Angeles City",15.1463554, 120.5245999),
            LocationModel("Metro Manila", 14.5965788,120.9445407),
            LocationModel("Cebu City",7.8370652,122.3735825)
        )

        val cityList = arrayOf("Angeles City", "Metro Manila", "Cebu City")

        val spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, cityList)
        binding.spnLocation.adapter = spinnerAdapter
        binding.spnLocation.onItemSelectedListener = this

    }

    private fun getWeatherData(lat: Double, lon : Double){
        val weatherAPI = RetrofitHelper.getInstance().create(OpenWeatherAPI::class.java)

        GlobalScope.launch(Dispatchers.IO){
            val result = weatherAPI.getFiveDayForecast(lat,lon, API_KEY, "metric")
            val weatherData = result.body()

            if(weatherData != null){
                forecastData.clear()
                forecastData.addAll(weatherData.list)
                withContext(Dispatchers.Main){
                    adapter.updateData(forecastData)
                }
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val locationData = locationList[p2]
        getWeatherData(locationData.lat,locationData.lon)
        showLoadingScreen()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    fun showLoadingScreen(){
        binding.progressBar.isVisible = true
        binding.rvForecast.isVisible = false
        object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                binding.progressBar.isVisible = false
                binding.rvForecast.isVisible = true

            }

        }.start()
    }
}