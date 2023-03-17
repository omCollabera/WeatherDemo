package com.collabera.weather

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.collabera.weather.database.InitDataBase
import com.collabera.weather.database.QueryDAO
import com.collabera.weather.repo.DBRepository
import com.collabera.weather.ui.loginReg.LoginDBViewModel
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginDBViewModelTestUi {

    lateinit var loginDBViewModel: LoginDBViewModel
    private lateinit var queryDAO: QueryDAO
    private lateinit var db: InitDataBase
    private lateinit var dbRepository: DBRepository

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, InitDataBase::class.java).build()
        queryDAO = db.getQueryDao()
        dbRepository= DBRepository(queryDAO)
        loginDBViewModel= LoginDBViewModel(dbRepository)
    }


    @Test
    fun inPutValidate() {
       var result= loginDBViewModel.validateInput("om","om@gmail.com","123","123",false)
        assertThat(result.first).isEqualTo(true)
        println("${result.second}")
    }

    @After
    fun tearDown() {
        db.close()
    }
}