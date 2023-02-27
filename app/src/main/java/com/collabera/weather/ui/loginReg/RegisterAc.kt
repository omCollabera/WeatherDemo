package com.collabera.weather.ui.loginReg

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.collabera.weather.databinding.ActivityRegisterBinding
import com.collabera.weather.ui.dashboard.ActivityDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterAc : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: LoginDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.submit.setOnClickListener {
            insertTb()
        }
        binding.goLogin.setOnClickListener {
            navigate(1)
        }
        viewModel.isRegister.observe(this) {
            if (it)
                navigate(4)
            else
                viewModel.validateMessage("Email already exist.")
        }
        viewModel.userList.observe(this) {
            //return data
        }
        viewModel.message.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun insertTb() {
        val name=binding.etFirstName.text.toString()
        val email=binding.etEmail.text.toString()
        val pass=binding.etPass.text.toString()
        val cPass=binding.etConPass.text.toString()
        if(name.isEmpty())
            viewModel.validateMessage("Enter name")
        else if (email.isEmpty())
            viewModel.validateMessage("Enter email")
        else if(pass.isEmpty() || cPass.isEmpty() || pass!=cPass)
            viewModel.validateMessage("Password is not valid")
        else
            viewModel.registerValidate(name,email,pass)
    }

    fun navigate(flag:Int){
        val mContext: Context = this@RegisterAc
        var intent: Intent?=null
        when (flag) {
            1 -> {
                intent= Intent(mContext, LoginAc::class.java)
            }
            4 -> {
                intent= Intent(mContext, ActivityDashboard::class.java)
            }
        }
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
        startActivity(intent)
    }


}