package com.tinkooladik.airqualityindex.domain.useCases

import com.tinkooladik.airqualityindex.domain.ObservableUseCase
import com.tinkooladik.airqualityindex.domain.SchedulersProvider
import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Observable
import javax.inject.Inject

class GetStationsDataUseCase @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val stationsDataProvider: StationsDataProvider
) : ObservableUseCase<GetStationsDataUseCase.Params, List<StationData>>(schedulersProvider) {

    override fun buildObservable(params: Params?): Observable<List<StationData>> {
        //get bounds from current location + radius
        return stationsDataProvider.getStationsData(
            LatLngBounds(
                39.379436, 116.091230, 40.235643, 116.784382
            )
        )
    }

    data class Params(
        val radius: Int,
        val minIndex: Int
    )
}