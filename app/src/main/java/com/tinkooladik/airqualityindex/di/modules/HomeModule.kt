package com.tinkooladik.airqualityindex.di.modules

import com.tinkooladik.airqualityindex.presentation.MainActivity
import com.tinkooladik.airqualityindex.presentation.home.HomeFragment
import com.tinkooladik.airqualityindex.presentation.station_details.StationDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface HomeModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    fun detailsFragment(): StationDetailsFragment
}