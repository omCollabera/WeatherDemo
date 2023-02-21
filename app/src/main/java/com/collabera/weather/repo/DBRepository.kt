package com.collabera.weather.repo
import com.collabera.weather.database.QueryDAO
import com.collabera.weather.models.TableModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DBRepository @Inject constructor(
    private val queryDAO: QueryDAO
){

    suspend fun insert(user: TableModel) = withContext(Dispatchers.IO){
        queryDAO.insert(user)
    }

     fun getUserById(id:Int): Flow<List<TableModel>> {
         return queryDAO.getUserById(id)
     }
}