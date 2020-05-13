package com.tinkooladik.airqualityindex.domain.useCases

import com.tinkooladik.airqualityindex.domain.ObservableUseCase
import com.tinkooladik.airqualityindex.domain.SchedulersProvider
import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Observable
import javax.inject.Inject

class GetStationsDataUseCase @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val stationsDataProvider: StationsDataProvider
//latlng bounds radius handler mb
) : ObservableUseCase<GetStationsDataUseCase.Params, List<StationData>>(schedulersProvider) {

    override fun buildObservable(params: Params?): Observable<List<StationData>> {
        return stationsDataProvider.getStationsData(39.379436, 116.091230, 40.235643, 116.784382)
    }

    data class Params(
        val lat1: Double,
        val lng1: Double,
        val lat2: Double,
        val lng2: Double
    )
}