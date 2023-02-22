package com.collabera.weather.di


import android.content.Context
import androidx.room.Room
import com.collabera.weather.database.InitDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideDatabase(  @ApplicationContext app: Context   ) =
        Room.databaseBuilder(
             app,
             InitDataBase::class.java,
             "UserDataBase"
         ).build()

    @Singleton
    @Provides
    fun provideQueryDao(db: InitDataBase) = db.getQueryDao()

}