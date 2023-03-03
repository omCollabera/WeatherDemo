package com.collabera.weather.repo

import com.collabera.weather.models.WeatherDataModel
import com.collabera.weather.network.apiInput.ApiService
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService
    ) {

    suspend fun getWeatherByLocation(lat:String,long:String,appId:String): Response<WeatherDataModel> =
        apiService.getWeatherByLocation("metric", lat, long, appId)
}