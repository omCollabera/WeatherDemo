package com.collabera.weather.repo
import com.collabera.weather.database.QueryDAO
import com.collabera.weather.models.TableModel
import com.collabera.weather.models.UserLocationTableModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DBRepository @Inject constructor(
    private val queryDAO: QueryDAO
){

    suspend fun registerUser(user: TableModel) : Long {
       return queryDAO.register(user)
       // return queryDAO.getUserById(id.toInt())
    }

     fun getUser(email:String,pass:String): Flow<List<TableModel>> {
         return queryDAO.getUser(email,pass)
     }

    //------------

   suspend  fun insertLocationData(data: UserLocationTableModel)  {
         queryDAO.insertLocationData(data)
    }

    fun getStoredLocation(email:String): Flow<List<UserLocationTableModel>> {
        return queryDAO.getStoredLocation(email)
    }


}