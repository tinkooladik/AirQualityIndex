package com.tinkooladik.airqualityindex

import android.app.Application
import com.tinkooladik.airqualityindex.di.AppGraph
import com.tinkooladik.airqualityindex.di.DaggerAppComponent
import com.tinkooladik.airqualityindex.di.HasAppGraph
import com.tinkooladik.airqualityindex.di.modules.AppModule
import com.tinkooladik.airqualityindex.di.modules.DataModule
import timber.log.Timber

class AirApp : Application(), HasAppGraph {

    lateinit var graph: AppGraph

    override fun onCreate() {
        super.onCreate()

        graph = createGraph()
        graph.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun appGraph(): AppGraph = graph

    private fun createGraph(): AppGraph =
        DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .appModule(AppModule(this))
            .build()
}