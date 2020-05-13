package com.tinkooladik.airqualityindex.di.modules

import com.tinkooladik.airqualityindex.BuildConfig
import com.tinkooladik.airqualityindex.data.ApiService
import com.tinkooladik.airqualityindex.data.ApiServiceFactory
import com.tinkooladik.airqualityindex.data.stations.StationsRepository
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideApiService(): ApiService = ApiServiceFactory.makeService(BuildConfig.DEBUG)
    }

    @Binds
    @Singleton
    abstract fun stationsDataProvider(provider: StationsRepository): StationsDataProvider
}