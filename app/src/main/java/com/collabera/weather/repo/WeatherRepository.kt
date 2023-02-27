package com.collabera.weather.repo

import com.collabera.weather.models.WeatherDataModel
import com.collabera.weather.network.apiInput.ApiService
import com.collabera.weather.util.Constants
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository
@Inject
constructor(private val apiService: ApiService) {

    suspend fun getWeatherByLocation(): Response<WeatherDataModel> = apiService.getWeatherByLocation(28.535517,77.391029,Constants.AppId)
}