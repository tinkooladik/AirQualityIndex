package com.tinkooladik.airqualityindex.domain.providers

import io.reactivex.Observable

interface StationsDataProvider {

    fun getStationsData(bounds: LatLngBounds): Observable<List<StationData>>
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
    val lat1: Double,
    val lng1: Double,
    val lat2: Double,
    val lng2: Double
) {
    fun asString() = "$lat1,$lng1,$lat2,$lng2"
    fun isWithin(lat: Double, lng: Double) = lat in lat1..lat2 && lng in lng1..lng2
}