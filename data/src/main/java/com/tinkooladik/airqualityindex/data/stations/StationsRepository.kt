package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.data.logError
import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class StationsRepository @Inject constructor(
    private val local: StationsLocalDataSource,
    private val remote: StationsRemoteDataSource
) : StationsDataProvider {

    //fixme I guess we shouldn't load data from api every time. mb add some check for last updated time
    override fun getStationsData(bounds: LatLngBounds): Observable<List<StationData>> {
        return local.getStations(bounds).concatWith(loadStations(bounds)).toObservable()
    }

    private fun loadStations(bounds: LatLngBounds) =
        Single.defer {
            logError("loadStations")
            remote.getStationsData(bounds)
                .flatMap { new ->
                    local.getStations(bounds)
                        .flatMap { old ->
                            local.removeStations(old)
                                .andThen(local.saveStations(new))
                                .andThen(Single.just(new))
                        }
                }
        }
}