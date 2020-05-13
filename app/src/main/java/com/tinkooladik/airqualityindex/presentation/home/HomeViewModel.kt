package com.tinkooladik.airqualityindex.presentation.home

import androidx.lifecycle.LiveData
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.data.logError
import com.tinkooladik.airqualityindex.domain.useCases.GetStationsDataUseCase
import com.tinkooladik.airqualityindex.util.SingleLiveEvent
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getStationsDataUseCase: GetStationsDataUseCase,
    private val homeStationVmMapper: HomeStationVmMapper
) : BaseViewModel() {

    val items: LiveData<List<HomeStationVM>>
        get() = _items

    private val _items = SingleLiveEvent<List<HomeStationVM>>()

    override fun initViewModel() {
        loading.set(true)
        getStationsDataUseCase.execute(
            GetStationsDataUseCase.Params(100, 100),
            mapper = homeStationVmMapper,
            onNext = { _items.value = it },
            onError = { logError("failed to load stations data", it) },
            onComplete = { loading.set(false) })
    }
}