package com.tinkooladik.airqualityindex.di

import com.tinkooladik.airqualityindex.di.modules.AppModule
import com.tinkooladik.airqualityindex.di.modules.DataModule
import com.tinkooladik.airqualityindex.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AppGraph