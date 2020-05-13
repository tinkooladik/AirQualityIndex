package com.tinkooladik.airqualityindex.di.modules

import android.app.Application
import androidx.room.Room
import com.tinkooladik.airqualityindex.BuildConfig
import com.tinkooladik.airqualityindex.data.local.AppDatabase
import com.tinkooladik.airqualityindex.data.remote.ApiService
import com.tinkooladik.airqualityindex.data.remote.ApiServiceFactory
import com.tinkooladik.airqualityindex.data.stations.*
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "app_db"

@Module
class DataModule(app: Application) {

    private val db = Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiServiceFactory.makeService(BuildConfig.DEBUG)

    @Provides
    @Singleton
    fun stationsLocalDataSource(
        localStationDataMapper: LocalStationDataMapper
    ): StationsLocalDataSource = StationsRoomDataSource(db.stationsDao(), localStationDataMapper)

    @Provides
    @Singleton
    fun stationsRemoteDataSource(source: StationsRemoteDataSourceImpl): StationsRemoteDataSource =
        source

    @Provides
    @Singleton
    fun stationsDataProvider(provider: StationsRepository): StationsDataProvider = provider
}