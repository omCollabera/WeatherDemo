package com.collabera.weather.database
import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.collabera.weather.models.TableModel
import kotlinx.coroutines.flow.Flow


@Dao
interface QueryDAO {

    @Query("SELECT * FROM userTable ORDER BY id ASC")
    fun getUser(): Flow<List<TableModel>>
    @Insert(onConflict = IGNORE)
   suspend fun register(user: TableModel) :Long

    @Query("SELECT * FROM userTable WHERE Id =:userId")
    fun getUserById(userId: Int): Flow<List<TableModel>>

    @Query("SELECT * FROM userTable WHERE email =:email AND password =:password")
    fun getUser(email:String,password:String): Flow<List<TableModel>>


    @Query("DELETE FROM userTable")
    suspend fun clearDb()
}
