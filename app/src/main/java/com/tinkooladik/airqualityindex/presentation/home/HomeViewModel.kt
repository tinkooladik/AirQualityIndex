package com.tinkooladik.airqualityindex.presentation.home

import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.data.logError
import com.tinkooladik.airqualityindex.data.logInfo
import com.tinkooladik.airqualityindex.domain.useCases.GetStationsDataUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getStationsDataUseCase: GetStationsDataUseCase
) : BaseViewModel() {

    override fun initViewModel() {
        getStationsDataUseCase.execute(GetStationsDataUseCase.Params(0.0, 0.0, 0.0, 0.0),
            onNext = { logInfo("stations loaded: ${it.size}") },
            onError = { logError("failed to load stations data", it) })
    }
}