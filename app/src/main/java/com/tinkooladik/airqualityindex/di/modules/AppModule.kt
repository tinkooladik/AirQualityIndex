package com.tinkooladik.airqualityindex.di.modules

import com.tinkooladik.airqualityindex.SchedulersProviderImpl
import com.tinkooladik.airqualityindex.domain.SchedulersProvider
import com.tinkooladik.airqualityindex.domain.providers.LocationBoundsProvider
import com.tinkooladik.airqualityindex.util.FusedLocationBoundsProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun scheduler(scheduler: SchedulersProviderImpl): SchedulersProvider = scheduler

    @Provides
    @Singleton
    fun locationBoundsProvider(provider: FusedLocationBoundsProvider): LocationBoundsProvider =
        provider
}