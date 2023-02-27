package com.collabera.weather.network.apiInput

import com.collabera.weather.models.WeatherDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
   suspend fun getWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("appid") appId: String
    ): Response<WeatherDataModel>

}