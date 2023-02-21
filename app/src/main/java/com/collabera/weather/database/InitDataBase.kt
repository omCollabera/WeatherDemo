package com.collabera.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.collabera.weather.models.TableModel

@Database(  entities = [TableModel::class],   version = 1 )
abstract class InitDataBase : RoomDatabase() {
    abstract fun getQueryDao(): QueryDAO
}