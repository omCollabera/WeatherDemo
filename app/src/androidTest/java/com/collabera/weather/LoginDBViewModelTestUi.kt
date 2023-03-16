package com.collabera.weather

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.collabera.weather.database.InitDataBase
import com.collabera.weather.database.QueryDAO
import com.collabera.weather.models.UserLocationTableModel
import com.collabera.weather.repo.DBRepository
import com.collabera.weather.ui.loginReg.LoginDBViewModel
import junit.framework.TestCase
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginDBViewModelTestUi : TestCase(){

    lateinit var loginDBViewModel: LoginDBViewModel
    private lateinit var queryDAO: QueryDAO
    private lateinit var db: InitDataBase
    private lateinit var dbRepository: DBRepository

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, InitDataBase::class.java).build()
        queryDAO = db.getQueryDao()
        dbRepository= DBRepository(queryDAO)
        loginDBViewModel= LoginDBViewModel(dbRepository)
    }

    @After
    public override fun tearDown() {
        db.close()
    }

    @Test
    fun loginValidate() {
        loginDBViewModel.registerValidate("om","op@gmail.com","123","123")
        val data= queryDAO.getUser("op@gmail.com","123",)

    }
}