package com.collabera.weather.database
import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.collabera.weather.models.TableModel
import com.collabera.weather.models.UserLocationTableModel
import com.collabera.weather.util.Constants.LocationTable
import com.collabera.weather.util.Constants.UserTable
import kotlinx.coroutines.flow.Flow

@Dao
interface QueryDAO {

    @Query("SELECT * FROM $UserTable ORDER BY id ASC")
    fun getUser(): Flow<List<TableModel>>
    @Insert(onConflict = IGNORE)
   suspend fun register(user: TableModel) :Long

    @Query("SELECT * FROM $UserTable WHERE Id =:userId")
    fun getUserById(userId: Int): Flow<List<TableModel>>

    @Query("SELECT * FROM $UserTable WHERE email =:email AND password =:password")
    fun getUser(email:String,password:String): Flow<List<TableModel>>

    //-------------------------
    @Insert(onConflict = IGNORE)
    suspend fun insertLocationData(userLocationTableModel: UserLocationTableModel)

    @Query("SELECT * FROM $LocationTable WHERE email =:email")
    fun getStoredLocation(email:String): Flow<List<UserLocationTableModel>>

    //------------------------
    @Query("DELETE FROM $UserTable")
    suspend fun clearDb()


}
