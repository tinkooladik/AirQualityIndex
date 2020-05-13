package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.data.logInfo
import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.StationData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

interface StationsLocalDataSource {

    fun getStations(bounds: LatLngBounds): Observable<List<StationData>>

    fun saveStations(stations: List<StationData>): Completable

    fun removeStations(stations: List<StationData>): Completable
}

class StationsRoomDataSource @Inject constructor(
    private val stationsDao: StationsDao,
    private val localStationDataMapper: LocalStationDataMapper
) : StationsLocalDataSource {

    override fun getStations(bounds: LatLngBounds): Observable<List<StationData>> =
        stationsDao.getAll(bounds.lat1, bounds.lng1, bounds.lat2, bounds.lng2)
            .flatMap { stations ->
                logInfo("got ${stations.size} stations from room")
                Observable.fromIterable(stations)
                    .map { localStationDataMapper.mapFrom(it) }
                    .toList()
                    .toObservable()
            }

    override fun saveStations(stations: List<StationData>): Completable =
        stationsDao.insert(stations.map { localStationDataMapper.mapTo(it) })
            .doOnComplete {
                logInfo("saved ${stations.size} stations to room")
            }

    override fun removeStations(stations: List<StationData>): Completable =
        stationsDao.delete(stations.map { localStationDataMapper.mapTo(it) })
            .doOnComplete {
                logInfo("removed ${stations.size} stations from room")
            }

}