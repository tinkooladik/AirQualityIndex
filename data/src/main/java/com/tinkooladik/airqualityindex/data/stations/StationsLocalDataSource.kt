package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.data.logInfo
import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.StationData
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

interface StationsLocalDataSource {

    fun getStationById(id: Int): Maybe<StationData>

    fun getStations(bounds: LatLngBounds): Maybe<List<StationData>>

    fun saveStations(stations: List<StationData>): Completable

    fun removeStations(stations: List<StationData>): Completable
}

class StationsRoomDataSource @Inject constructor(
    private val stationsDao: StationsDao,
    private val localStationDataMapper: LocalStationDataMapper
) : StationsLocalDataSource {

    override fun getStationById(id: Int): Maybe<StationData> =
        stationsDao.getById(id).map { localStationDataMapper.mapFrom(it) }
            .doOnEvent { t1, _ -> logInfo("got ${t1.name} station for id=$id from room") }

    override fun getStations(bounds: LatLngBounds): Maybe<List<StationData>> =
        stationsDao.getAllInBounds(
            bounds.southwest.lat,
            bounds.southwest.lng,
            bounds.northeast.lat,
            bounds.northeast.lng
        )
            .map { stations ->
                logInfo("got ${stations.size} stations from room")
                stations.map { localStationDataMapper.mapFrom(it) }
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