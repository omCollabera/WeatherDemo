package com.collabera.weather.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.collabera.weather.util.Constants.UserTable

@Entity(tableName = UserTable , indices = [Index(value = ["email"], unique = true)])
data class TableModel(
    var name: String,
    @ColumnInfo(name = "email")
    var email: String,
    var password:String
    ){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}




