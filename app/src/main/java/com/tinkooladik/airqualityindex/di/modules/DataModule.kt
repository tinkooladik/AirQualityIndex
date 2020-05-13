package com.tinkooladik.airqualityindex.di.modules

import com.tinkooladik.airqualityindex.BuildConfig
import com.tinkooladik.airqualityindex.data.ApiService
import com.tinkooladik.airqualityindex.data.ApiServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        ApiServiceFactory.makeService(
            "",//BuildConfig.API_ENDPOINT,
            BuildConfig.DEBUG
        )
}