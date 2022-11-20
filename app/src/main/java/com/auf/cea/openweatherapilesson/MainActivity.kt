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
import com.auf.cea.openweatherapilesson.constants.BASE_IMAGE_URL
import com.auf.cea.openweatherapilesson.databinding.ActivityMainBinding
import com.auf.cea.openweatherapilesson.models.ForecastModel
import com.auf.cea.openweatherapilesson.models.LocationModel
import com.auf.cea.openweatherapilesson.services.helper.RetrofitHelper
import com.auf.cea.openweatherapilesson.services.repository.OpenWeatherAPI
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WeatherAdapter
    private lateinit var forecastData: ArrayList<ForecastModel>
    private lateinit var locationList: ArrayList<LocationModel>
    private lateinit var todayWeather: ForecastModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

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
                todayWeather = weatherData.list[0]
                updateTodayWeather(todayWeather)
                forecastData.clear()
                forecastData.addAll(weatherData.list)
                withContext(Dispatchers.Main){
                    adapter.updateData(forecastData)
                }

            }
            binding.txtWeatherType.text = todayWeather.weather[0].main
            binding.txtTemp.text = String.format("%s°C",todayWeather.main.temp)
            binding.txtFeelsLike.text = String.format("\uD83C\uDF21\n%s°C",todayWeather.main.feels_like)
            binding.txtMinTemp.text = String.format("\uD83D\uDD3D\n%s°C",todayWeather.main.temp_min)
            binding.txtMaxTemp.text = String.format("\uD83D\uDD3C\n%s°C",todayWeather.main.temp_max)


        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val locationData = locationList[p2]
        getWeatherData(locationData.lat,locationData.lon)
        showLoadingScreen()
    }

    private fun updateTodayWeather(todayWeather: ForecastModel){
//        Glide.with(this@MainActivity)
//            .load(BASE_IMAGE_URL+ todayWeather.weather[0].icon+".png")
//            .into(binding.imgweather)

//        binding.txtDay.text = getDay(todayWeather.dt)
//        binding.txtTime.text = getTime(todayWeather.dt)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    fun showLoadingScreen(){
        binding.progressBar.isVisible = true
        binding.rvForecast.isVisible = false
        binding.imgInfo.isVisible = false
        object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                binding.progressBar.isVisible = false
                binding.rvForecast.isVisible = true
                binding.imgInfo.isVisible = true
            }

        }.start()
    }

    private fun getDay(timeStamp: Long): String{
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timeStamp * 1000)
    }

    private fun getTime(timeStamp: Long): String{
        return SimpleDateFormat("hh:mm aa", Locale.ENGLISH).format(timeStamp * 1000)
    }
}