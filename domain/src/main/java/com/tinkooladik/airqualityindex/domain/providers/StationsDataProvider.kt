package com.tinkooladik.airqualityindex.domain.providers

import io.reactivex.Observable

interface StationsDataProvider {

    fun getStationsData(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Observable<List<StationData>>
}

data class StationData(
    val id: Int,
    val name: String,
    val lat: Double,
    val lng: Double,
    val index: Int,
    val lastUpdated: Long
)