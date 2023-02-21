package com.collabera.weather.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.collabera.weather.databinding.ActivityLoginBinding
import com.collabera.weather.models.TableModel
import com.collabera.weather.ui.viewModel.LoginDBViewModel

class LoginAc : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initView()
    }

    private fun initView() {
        binding.submitLogin.setOnClickListener {
            insertTb()
        }

        viewModel.userList.observe(this, Observer {
            // textView?.text=it.size.toString()+"\n"+it.toString()
        })

    }

    private fun insertTb() {
        val user= TableModel("opMishra","","pass")
        viewModel.loginValidate(user)
    }
}


