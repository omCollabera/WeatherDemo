package com.collabera.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.collabera.weather.models.TableModel
import com.collabera.weather.models.UserLocationTableModel
import com.collabera.weather.util.Constants.DataBaseVersion

@Database(  entities = [TableModel::class, UserLocationTableModel::class],   version = DataBaseVersion )
abstract class InitDataBase : RoomDatabase() {
    abstract fun getQueryDao(): QueryDAO
}