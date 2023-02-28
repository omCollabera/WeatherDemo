package com.collabera.weather.database

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalSharedPreference @Inject constructor(@ApplicationContext context : Context){
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getString(key: String): String {
        return prefs.getString(key, "")!!
    }
    fun setString(key: String,value: String) {
        prefs.edit().putString(key, value).apply()
    }

}