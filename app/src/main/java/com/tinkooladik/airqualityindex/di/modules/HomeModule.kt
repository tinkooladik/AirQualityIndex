package com.tinkooladik.airqualityindex.di.modules

import androidx.lifecycle.ViewModelProvider
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.data.local.AppDatabase
import com.tinkooladik.airqualityindex.data.stations.*
import com.tinkooladik.airqualityindex.di.ActivityScope
import com.tinkooladik.airqualityindex.di.FragmentScope
import com.tinkooladik.airqualityindex.di.ViewModelFactory
import com.tinkooladik.airqualityindex.di.ViewModelKey
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import com.tinkooladik.airqualityindex.presentation.home.HomeFragment
import com.tinkooladik.airqualityindex.presentation.home.HomeViewModel
import com.tinkooladik.airqualityindex.presentation.station_details.StationDetailsFragment
import com.tinkooladik.airqualityindex.presentation.station_details.StationDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule : HomeModuleDependencies() {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun detailsFragment(): StationDetailsFragment

    @ActivityScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm: HomeViewModel): BaseViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(StationDetailsViewModel::class)
    abstract fun bindDetailsViewModel(vm: StationDetailsViewModel): BaseViewModel
}

@Module
abstract class HomeModuleDependencies {

    companion object {

        @ActivityScope
        @Provides
        @JvmStatic
        fun stationsLocalDataSource(
            db: AppDatabase,
            localStationDataMapper: LocalStationDataMapper
        ): StationsLocalDataSource =
            StationsRoomDataSource(db.stationsDao(), localStationDataMapper)
    }

    @ActivityScope
    @Binds
    abstract fun stationsRemoteDataSource(source: StationsRemoteDataSourceImpl): StationsRemoteDataSource

    @ActivityScope
    @Binds
    abstract fun stationsDataProvider(provider: StationsRepository): StationsDataProvider
}