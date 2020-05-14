package com.tinkooladik.airqualityindex.di

import com.tinkooladik.airqualityindex.AirApp
import com.tinkooladik.airqualityindex.presentation.MainActivity
import com.tinkooladik.airqualityindex.presentation.home.HomeFragment
import com.tinkooladik.airqualityindex.presentation.station_details.StationDetailsFragment

interface HasAppGraph {
    fun appGraph(): AppGraph
}

interface AppGraph {

    fun inject(app: AirApp)

    fun inject(mainActivity: MainActivity)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: StationDetailsFragment)
}