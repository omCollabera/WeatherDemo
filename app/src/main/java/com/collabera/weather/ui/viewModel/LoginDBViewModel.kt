package com.collabera.weather.ui.viewModel

import android.content.Intent
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collabera.weather.models.TableModel
import com.collabera.weather.repo.DBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginDBViewModel @Inject constructor(
    private val repository: DBRepository,
): ViewModel() {

    var firstName: MutableLiveData<String> = MutableLiveData()
    var lastName: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var username: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

   private val _userList= MutableLiveData<List<TableModel>>()
    val userList: LiveData<List<TableModel>> get()=_userList



    fun loginValidate(user: TableModel) {
        val name = username.value.toString()
        val pass = password.value.toString()
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {

        } else {
            navigate(4)
            //responseData()
            //signInInAPI()
        }
    }

    fun registerValidate(user: TableModel) {

    }

    fun navigate(flag:Int){
        var intent: Intent?=null

        /*  when (flag) {
              1 -> {
                  intent= Intent(context, LoginActivity::class.java)
              }
              2 -> {
                  intent= Intent(context, SignUpAc::class.java)
              }
              3 -> {
                  intent= Intent(context, ForgotPassAc::class.java)
              }
              4 -> {
                  intent= Intent(context, DashBoardActivity::class.java)
              }
          }
          intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
          context.startActivity(intent)*/
    }



    fun registerDb(user:TableModel){
        viewModelScope.launch {
            repository.insert(user)
        }
    }



    fun loginDb( id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserById(id).collect(_userList::postValue)
        }
    }



}