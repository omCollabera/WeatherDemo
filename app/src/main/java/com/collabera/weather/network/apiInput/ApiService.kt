package com.collabera.weather.network.apiInput

import com.collabera.weather.models.WeatherDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
   suspend fun getWeatherByLocation(
        @Query("units") unit: String,
        @Query("lat") lat: String,
        @Query("lon") lng: String,
        @Query("appid") appId: String
    ): Response<WeatherDataModel>

}