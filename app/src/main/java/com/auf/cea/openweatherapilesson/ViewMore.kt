package com.auf.cea.openweatherapilesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auf.cea.openweatherapilesson.databinding.ActivityViewMoreBinding

class ViewMore : AppCompatActivity() {
    private lateinit var binding: ActivityViewMoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weather = intent.getSerializableExtra("forecast_model_data")
        binding.txtVisibility.text = String.format("Visibility: %s", weather.toString())
        binding.txtPopulation.text = String.format("Population: %s", weather.toString())
        binding.txtFeelsLike.text = String.format("Feels Like: %s", weather.toString())
    }
}