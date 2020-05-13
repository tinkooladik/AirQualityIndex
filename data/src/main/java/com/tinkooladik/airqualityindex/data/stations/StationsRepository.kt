package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Observable
import javax.inject.Inject

class StationsRepository @Inject constructor(
    private val local: StationsLocalDataSource,
    private val remote: StationsRemoteDataSource
) : StationsDataProvider {

    override fun getStationsData(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Observable<List<StationData>> {
        return remote.getStationsData(lat1, lng1, lat2, lng2).toObservable()
    }

}