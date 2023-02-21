package com.collabera.weather.database
import androidx.room.*
import com.collabera.weather.models.TableModel
import kotlinx.coroutines.flow.Flow


@Dao
interface QueryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: TableModel)

    @Query("SELECT * FROM userTable WHERE id =:id")
    fun getUserById(id: Int): Flow<List<TableModel>>
}
