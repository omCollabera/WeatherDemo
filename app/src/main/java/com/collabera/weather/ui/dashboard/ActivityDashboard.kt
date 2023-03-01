package com.collabera.weather.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.collabera.weather.databinding.ActivityDashboardBinding
import com.collabera.weather.ui.dashboard.adapter.DashboardPagerAdapter
import com.collabera.weather.ui.loginReg.LoginAc
import com.collabera.weather.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logOut.setOnClickListener {
            intent= Intent(this, LoginAc::class.java)
            finish()
            startActivity(intent)
        }

        initView()
    }

    private fun initView() {

        val adapter= DashboardPagerAdapter(this, supportFragmentManager)
        binding.tabsMain.setupWithViewPager(binding.vpMain)
        binding.vpMain.adapter=adapter
    }
}