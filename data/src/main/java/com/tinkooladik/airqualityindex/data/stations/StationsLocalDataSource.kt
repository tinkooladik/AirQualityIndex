package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Observable
import javax.inject.Inject

class StationsLocalDataSource @Inject constructor(
) : StationsDataProvider {
    override fun getStationsData(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Observable<List<StationData>> {
        return Observable.just(listOf())
    }
}