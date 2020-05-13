package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Observable
import javax.inject.Inject

class StationsRepository @Inject constructor(
    private val local: StationsLocalDataSource,
    private val remote: StationsRemoteDataSource
) : StationsDataProvider {

    override fun getStationsData(bounds: LatLngBounds): Observable<List<StationData>> {
        return Observable.concatArrayEager(
            local.getStations(bounds),
            loadStations(bounds)
        )
    }

    private fun loadStations(bounds: LatLngBounds) =
        Observable.defer {
            remote.getStationsData(bounds)
                .flatMapObservable { new ->
                    local.getStations(bounds)
                        .take(1)
                        .flatMap { old ->
                            local.removeStations(old)
                                .andThen(local.saveStations(new))
                                .andThen(Observable.just(new))
                        }
                }
        }
}