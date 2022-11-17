package com.auf.cea.openweatherapilesson.services.helper

import com.auf.cea.openweatherapilesson.constants.BASE_API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}