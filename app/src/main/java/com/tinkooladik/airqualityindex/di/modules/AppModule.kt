package com.tinkooladik.airqualityindex.di.modules

import android.content.Context
import com.tinkooladik.airqualityindex.AirApp
import com.tinkooladik.airqualityindex.SchedulersProviderImpl
import com.tinkooladik.airqualityindex.di.ActivityScope
import com.tinkooladik.airqualityindex.di.AppScope
import com.tinkooladik.airqualityindex.domain.SchedulersProvider
import com.tinkooladik.airqualityindex.domain.providers.LocationBoundsProvider
import com.tinkooladik.airqualityindex.presentation.MainActivity
import com.tinkooladik.airqualityindex.util.FusedLocationBoundsProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppModule {

    @AppScope
    @Binds
    fun context(app: AirApp): Context

    @AppScope
    @Binds
    fun scheduler(scheduler: SchedulersProviderImpl): SchedulersProvider

    @AppScope
    @Binds
    fun locationBoundsProvider(provider: FusedLocationBoundsProvider): LocationBoundsProvider

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    fun contributeMainActivity(): MainActivity
}