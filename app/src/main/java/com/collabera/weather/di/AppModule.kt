package com.collabera.weather.di


import android.content.Context
import androidx.room.Room
import com.collabera.weather.common.MySharedPreference
import com.collabera.weather.database.InitDataBase
import com.collabera.weather.network.apiInput.ApiService
import com.collabera.weather.util.Constants
import com.collabera.weather.util.Constants.DataBaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //------------Room Database Module
    @Singleton
    @Provides
    fun provideDatabase(  @ApplicationContext app: Context   ) =
        Room.databaseBuilder(app, InitDataBase::class.java,DataBaseName).build()

    @Singleton
    @Provides
    fun provideQueryDao(db: InitDataBase) = db.getQueryDao()


    //------------API Module
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService =
        Retrofit.Builder()  .baseUrl(BASE_URL)  .addConverterFactory(GsonConverterFactory.create())   .build()
            .create(ApiService::class.java)

    //------------Preference Module
    @Provides
    fun providePreference(@ApplicationContext context: Context )= MySharedPreference(context)

}