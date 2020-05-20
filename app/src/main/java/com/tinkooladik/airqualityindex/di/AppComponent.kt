package com.tinkooladik.airqualityindex.di

import com.tinkooladik.airqualityindex.AirApp
import com.tinkooladik.airqualityindex.di.modules.AppModule
import com.tinkooladik.airqualityindex.di.modules.DataModule
import com.tinkooladik.airqualityindex.di.modules.HomeModule
import com.tinkooladik.airqualityindex.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        HomeModule::class,
        AppModule::class,
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<AirApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<AirApp>
}