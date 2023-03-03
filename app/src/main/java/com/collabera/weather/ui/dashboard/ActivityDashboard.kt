package com.collabera.weather.ui.dashboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.collabera.weather.databinding.ActivityDashboardBinding
import com.collabera.weather.ui.dashboard.adapter.DashboardPagerAdapter
import com.collabera.weather.ui.loginReg.LoginAc
import com.collabera.weather.util.Constants.PERMISSION_REQUEST_ACCESS_FINE_LOCATION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashBoardViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.logOut.setOnClickListener {
            intent= Intent(this, LoginAc::class.java)
            finish()
            startActivity(intent)
        }

        val adapter= DashboardPagerAdapter(this, supportFragmentManager)
        binding.tabsMain.setupWithViewPager(binding.vpMain)
        binding.vpMain.adapter=adapter

        getLotLong()
    }

    private fun getLotLong() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        val locationListener = LocationListener { location ->
            viewModel.storeLatLong(location.latitude.toString(),location.longitude.toString())
        }
        try {
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (_:SecurityException) { }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED ->
                    getLotLong()
                PackageManager.PERMISSION_DENIED -> {
                    Toast.makeText(
                        applicationContext,
                        "Please allow permission for current location weather access.",
                        Toast.LENGTH_SHORT
                    ).show()
                    getLotLong()
                }
            }
        }
    }


}