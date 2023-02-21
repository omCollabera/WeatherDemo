package com.collabera.weather.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.collabera.weather.R
import com.collabera.weather.databinding.ActivityLoginBinding
import com.collabera.weather.databinding.ActivityMainBinding
import com.collabera.weather.databinding.ActivitySplashScreenBinding

class SplashAc : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}