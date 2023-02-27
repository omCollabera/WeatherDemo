package com.collabera.weather.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collabera.weather.models.WeatherDataModel
import com.collabera.weather.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel
@Inject
constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _response = MutableLiveData<WeatherDataModel>()
    val weatherResponse: LiveData<WeatherDataModel>
        get() = _response


    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        repository.getWeatherByLocation().let { response ->
            if (response.isSuccessful) {
                Log.d("===>", "response: ${response.body()}")
                _response.postValue(response.body())
            } else {
                Log.d("===>", "Error Type Code: ${response.code()}")
            }
        }
    }


    fun getCreatedDate(type: String,timeUtc:String): String? {
        val output = SimpleDateFormat("dd MMM yyyy, hh:mm aa")
        val input: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        input.timeZone = TimeZone.getTimeZone("UTC")
        var d: Date? = null
        try {
            d = input.parse(timeUtc)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return type + output.format(d)
    }


}













