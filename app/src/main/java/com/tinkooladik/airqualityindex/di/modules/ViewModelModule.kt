package com.tinkooladik.airqualityindex.di.modules

import androidx.lifecycle.ViewModelProvider
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.di.ViewModelFactory
import com.tinkooladik.airqualityindex.di.ViewModelKey
import com.tinkooladik.airqualityindex.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm: HomeViewModel): BaseViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}