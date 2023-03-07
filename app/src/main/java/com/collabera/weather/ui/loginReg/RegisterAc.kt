package com.collabera.weather.ui.loginReg

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.collabera.weather.databinding.ActivityRegisterBinding
import com.collabera.weather.ui.dashboard.ActivityDashboard
import com.collabera.weather.util.Constants
import com.collabera.weather.util.UtilsKt.isValidEmail
import com.collabera.weather.util.UtilsKt.isValidName
import com.collabera.weather.util.UtilsKt.isValidPass
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
        if(!isValidName(name))
            viewModel.validateMessage("Enter name")
        else if (!isValidEmail(email))
            viewModel.validateMessage("Enter valid email")
        else if(!isValidPass(pass) || !isValidPass(pass) || pass!=cPass)
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
                viewModel.preference.setString(Constants.PrimaryEmail,binding.etEmail.text.toString())
            }
        }
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
        startActivity(intent)
    }


}