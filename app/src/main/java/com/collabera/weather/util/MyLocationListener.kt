package com.collabera.weather.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import java.io.IOException
import java.util.*

class MyLocationListener internal constructor(var context: Context) : LocationListener {
    override fun onLocationChanged(loc: Location) {
        var editLocation: String? = "-"
        val longitude = "Longitude: " + loc.longitude
        val latitude = "Latitude: " + loc.latitude

        /*------- To get city name from coordinates -------- */
        var cityName: String? = null
        val gcd = Geocoder(context.applicationContext, Locale.getDefault())
        val addresses: List<Address>?
        try {
            addresses = gcd.getFromLocation(
                loc.latitude,
                loc.longitude, 1
            )
            if (addresses!!.size > 0) {
                println(addresses[0].locality)
                cityName = addresses[0].locality
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val s = """
              $longitude
              $latitude
              
              My Current City is: $cityName
              """.trimIndent()
        editLocation = s
    }

    override fun onProviderDisabled(provider: String) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
}