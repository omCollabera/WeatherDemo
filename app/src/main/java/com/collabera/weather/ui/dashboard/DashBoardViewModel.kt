package com.collabera.weather.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collabera.weather.database.LocalSharedPreference
import com.collabera.weather.models.UserLocationTableModel
import com.collabera.weather.models.WeatherDataModel
import com.collabera.weather.repo.DBRepository
import com.collabera.weather.repo.WeatherRepository
import com.collabera.weather.util.Constants.PrimaryEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel
@Inject
constructor(
    val localSharedPreference: LocalSharedPreference,
    private val networkRepo: WeatherRepository,
    private val dbRepository: DBRepository
    
) : ViewModel() {

    private val _response = MutableLiveData<WeatherDataModel>()
    val weatherResponse: LiveData<WeatherDataModel>
        get() = _response



    init {
        getWeather()
        getStoredLocation(localSharedPreference.getString(PrimaryEmail))
    }

    private fun getWeather() = viewModelScope.launch {
        networkRepo.getWeatherByLocation().let { response ->
            if (response.isSuccessful) {
                Log.d("===>getWeatherAPI", "response: ${response.body()}")
                _response.postValue(response.body())
            } else {
                Log.d("===>", "Error Type Code: ${response.code()}")
            }
        }
    }

    fun enterUserLocation(locationData: UserLocationTableModel){
        viewModelScope.launch(Dispatchers.IO) {
        dbRepository.insertLocationData(locationData)
        }
    }

    private val _weatherList= MutableLiveData<List<UserLocationTableModel>>()
    val weatherList: LiveData<List<UserLocationTableModel>> get()=_weatherList
    private fun getStoredLocation(email:String) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getStoredLocation(email).collect { item ->
                if (item!=null && item.isNotEmpty()) {
                    _weatherList.postValue(item)
                }
            }
        }
    }

    //------------------------------------------------
    fun utcFormatted(time: Long,tmPattern:String): String? {
        return SimpleDateFormat(tmPattern, Locale.ENGLISH).format(Date(time * 1000))
    }


}













