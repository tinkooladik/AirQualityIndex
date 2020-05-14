package com.tinkooladik.airqualityindex.domain.providers

import io.reactivex.Maybe
import io.reactivex.Observable

interface StationsDataProvider {

    fun getStationsData(bounds: LatLngBounds): Observable<List<StationData>>

    fun getStationById(id: Int): Maybe<StationData>
}

data class StationData(
    val id: Int,
    val name: String,
    val lat: Double,
    val lng: Double,
    val index: Int,
    val lastUpdated: Long
)

data class LatLngBounds(
    val southwest: LatLng,
    val northeast: LatLng
) {
    fun asString() = "${southwest.lat},${southwest.lng},${northeast.lat},${northeast.lng}"
}

data class LatLng(
    val lat: Double,
    val lng: Double
)