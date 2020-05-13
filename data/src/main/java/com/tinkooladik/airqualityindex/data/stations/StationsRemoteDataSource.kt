package com.tinkooladik.airqualityindex.data.stations

import android.annotation.SuppressLint
import com.tinkooladik.airqualityindex.data.ApiService
import com.tinkooladik.airqualityindex.data.Status
import com.tinkooladik.airqualityindex.domain.InvalidApiResponseException
import com.tinkooladik.airqualityindex.domain.providers.StationData
import io.reactivex.Single
import javax.inject.Inject

class StationsRemoteDataSource @Inject constructor(
    private val api: ApiService,
    private val apiStationDataMapper: ApiStationDataMapper
) {

    @SuppressLint("DefaultLocale")
    fun getStationsData(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Single<List<StationData>> {
        return api.getStationsData("$lat1,$lng1,$lat2,$lng2")
            .map {
                when (it.status.toUpperCase()) {
                    Status.OK.name -> it.data?.map { apiStationDataMapper.mapTo(it) }
                    else -> throw InvalidApiResponseException(it.error)
                }
            }
    }
}