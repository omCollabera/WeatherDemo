package com.collabera.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.collabera.weather.models.TableModel
import com.collabera.weather.models.UserLocationTableModel

@Database(  entities = [TableModel::class, UserLocationTableModel::class],   version = 1 )
abstract class InitDataBase : RoomDatabase() {
    abstract fun getQueryDao(): QueryDAO
}