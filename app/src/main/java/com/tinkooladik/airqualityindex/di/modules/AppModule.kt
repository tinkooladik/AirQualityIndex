package com.tinkooladik.airqualityindex.di.modules

import com.tinkooladik.airqualityindex.SchedulersProviderImpl
import com.tinkooladik.airqualityindex.domain.SchedulersProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun scheduler(scheduler: SchedulersProviderImpl): SchedulersProvider
}