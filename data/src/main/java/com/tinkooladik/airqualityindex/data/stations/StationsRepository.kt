package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class StationsRepository @Inject constructor(
    private val local: StationsLocalDataSource,
    private val remote: StationsRemoteDataSource
) : StationsDataProvider {

    //fixme I guess we shouldn't load data from api every time. mb add some check for last updated time
    override fun getStationsData(bounds: LatLngBounds): Observable<List<StationData>> {
        return Observable.concat(
            local.getStations(bounds).toObservable(),
            loadStations(bounds).toObservable()
        )
    }

    override fun getStationById(id: Int): Maybe<StationData> = local.getStationById(id)

    private fun loadStations(bounds: LatLngBounds): Single<List<StationData>> =
        Single.defer {
            remote.getStationsData(bounds)
                .flatMap { new ->
                    local.getStations(bounds)
                        .flatMapCompletable { old ->
                            local.removeStations(old)
                                .andThen(local.saveStations(new))
                        }
                        .andThen(Single.just(new))
                }
        }
}