package com.collabera.weather.repo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.collabera.weather.database.InitDataBase
import com.collabera.weather.database.QueryDAO
import com.collabera.weather.models.TableModel
import com.collabera.weather.models.UserLocationTableModel
import com.collabera.weather.ui.loginReg.LoginDBViewModel
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DBRepositoryTest : TestCase(){
    lateinit var loginDBViewModel: LoginDBViewModel
    private lateinit var queryDAO: QueryDAO
    private lateinit var db: InitDataBase
    private lateinit var dbRepository: DBRepository
    //------
    private lateinit var userTable: TableModel
    private lateinit var userLocationTableModel: UserLocationTableModel

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, InitDataBase::class.java).build()
        queryDAO = db.getQueryDao()
        dbRepository= DBRepository(queryDAO)
        loginDBViewModel= LoginDBViewModel(dbRepository)
    }



    @Test
    fun registerUser() =runBlocking{
        userTable=TableModel("c1","unit1@gmail.com","123")
       val result1=queryDAO.register(userTable)
        assertThat(result1).isGreaterThan(0)

        val result2=queryDAO.getUser("unit1@gmail.com","123").first()
        assertThat(result2).hasSize(1)

    }





    @Test
    fun insertLocationData() = runBlocking{
       var locationTableModel=UserLocationTableModel(
           "d","unit1@gmail.com","100",
       "unit test c1","India","Delhi-Noida",
       "7am","6pm","10:10:10")
        queryDAO.insertLocationData(locationTableModel)

        val result2=queryDAO.getStoredLocation("unit1@gmail.com").first()
        assertThat(result2).hasSize(1)
        println("====>${result2.toList()} ---${result2.size}" )
    }


    @After
    public override fun tearDown() {
        db.close()
    }
}