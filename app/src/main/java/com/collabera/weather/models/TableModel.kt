package com.collabera.weather.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class TableModel(var name: String, var email: String, var password:String)  {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

