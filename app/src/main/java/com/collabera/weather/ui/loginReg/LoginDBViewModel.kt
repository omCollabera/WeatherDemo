package com.collabera.weather.ui.loginReg

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.collabera.weather.models.TableModel
import com.collabera.weather.repo.DBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class LoginDBViewModel @Inject constructor(
    private val repository: DBRepository,
): ViewModel() {



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
            viewModelScope.launch(Dispatchers.IO) {
                repository.getUser(email,password).collect(_userList::postValue)

        }
    }

    fun registerValidate(  name: String,   email: String,
        pass: String
    ) {
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




}