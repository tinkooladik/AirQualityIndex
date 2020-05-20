package com.tinkooladik.airqualityindex.di

import com.tinkooladik.airqualityindex.AirApp
import com.tinkooladik.airqualityindex.di.modules.AppModule
import com.tinkooladik.airqualityindex.di.modules.DataModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<AirApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<AirApp>
}