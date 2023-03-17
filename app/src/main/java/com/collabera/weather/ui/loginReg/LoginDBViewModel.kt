package com.collabera.weather.ui.loginReg

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.collabera.weather.common.MySharedPreference
import com.collabera.weather.models.TableModel
import com.collabera.weather.repo.DBRepository
import com.collabera.weather.util.UtilsKt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class LoginDBViewModel @Inject constructor(
    private val repository: DBRepository
): ViewModel() {

    @Inject
    lateinit var preference: MySharedPreference

   private val _userList= MutableLiveData<List<TableModel>>()
    val userList: LiveData<List<TableModel>> get()=_userList

    private val _isRegister= MutableLiveData<Boolean>()
    val isRegister: LiveData<Boolean> get()=_isRegister

    private val _message= MutableLiveData<String>()
    val message: LiveData<String> get()=_message

    fun validateMessage(message:String){
        _message.postValue(message)
    }

    fun loginValidate(email:String,password:String) {
        val check=validateInput("",email,password,password,true)
        if(!check.first){
            _message.postValue(check.second!!)
            return
        }
            viewModelScope.launch(Dispatchers.IO) {
                repository.getUser(email,password).collect(_userList::postValue)
        }
    }



    @SuppressLint("SuspiciousIndentation")
    fun registerValidate(name: String, email: String, pass: String, cPass: String) {
        val check=validateInput(name,email,pass,cPass,false)
            if(!check.first){
                _message.postValue(check.second!!)
                return
            }
            val userData =TableModel(name = name, email = email, password = pass)
            registerUser(userData)
    }


    @SuppressLint("SuspiciousIndentation")
    private fun registerUser(userData: TableModel) = viewModelScope.launch {
        repository.registerUser(userData).let { response ->
            if(response>0) {
                _userList.postValue(listOf(userData))
                _isRegister.postValue(true)
            }else
            _isRegister.postValue(false)
        }
    }

    fun validateInput(userName: String, emailAddress: String,  password: String, cPass: String,
                            isLogin: Boolean) : Pair<Boolean, String> {

        var result = Pair(true, "")
        if(!isLogin && !UtilsKt.isValidName(userName)){
            result = Pair(false, "Enter valid name")
        }

        else if(!UtilsKt.isValidEmail(emailAddress)){
            result = Pair(false, "Email is not invalid")
        }
        else if(!UtilsKt.isValidPass(password) || !UtilsKt.isValidPass(cPass) || password!=cPass){
            result = Pair(false, "Password is not valid")
        }
        return result
    }

}