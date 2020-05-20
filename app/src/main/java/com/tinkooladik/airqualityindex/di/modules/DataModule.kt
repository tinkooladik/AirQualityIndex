package com.tinkooladik.airqualityindex.di.modules

import android.content.Context
import androidx.room.Room
import com.tinkooladik.airqualityindex.BuildConfig
import com.tinkooladik.airqualityindex.data.DateFormatProvider
import com.tinkooladik.airqualityindex.data.local.AppDatabase
import com.tinkooladik.airqualityindex.data.remote.ApiService
import com.tinkooladik.airqualityindex.data.remote.ApiServiceFactory
import com.tinkooladik.airqualityindex.di.AppScope
import com.tinkooladik.airqualityindex.util.DateFormatProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

private const val DB_NAME = "app_db"

@Module
abstract class DataModule {

    @Module
    companion object {

        @AppScope
        @Provides
        @JvmStatic
        fun provideDb(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

        @AppScope
        @Provides
        @JvmStatic
        fun provideApiService(): ApiService = ApiServiceFactory.makeService(BuildConfig.DEBUG)
    }

    @AppScope
    @Binds
    abstract fun dateFormatProvider(dateFormatProviderImpl: DateFormatProviderImpl): DateFormatProvider
}