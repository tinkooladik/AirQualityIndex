package com.tinkooladik.airqualityindex.presentation.station_details

import androidx.databinding.ObservableField
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.data.logError
import com.tinkooladik.airqualityindex.domain.NoSuchValueException
import com.tinkooladik.airqualityindex.domain.useCases.GetStationDetailsUseCase
import com.tinkooladik.airqualityindex.presentation.home.StationVM
import com.tinkooladik.airqualityindex.presentation.home.StationVmMapper
import javax.inject.Inject


class StationDetailsViewModel @Inject constructor(
    private val getStationDetailsUseCase: GetStationDetailsUseCase,
    private val stationVmMapper: StationVmMapper
) : BaseViewModel() {

    var station = ObservableField<StationVM>()

    fun loadStationData(id: Int) {
        getStationDetailsUseCase.execute(
            id,
            mapper = stationVmMapper,
            onNext = { station.set(it) },
            onError = {
                _error.value = NoSuchValueException()
                logError("failed to load stations data", it)
            }
        )
    }
}