package com.tinkooladik.airqualityindex.data.stations

import android.annotation.SuppressLint
import com.tinkooladik.airqualityindex.data.logInfo
import com.tinkooladik.airqualityindex.data.remote.ApiService
import com.tinkooladik.airqualityindex.data.remote.Status
import com.tinkooladik.airqualityindex.domain.InvalidApiResponseException
import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.StationData
import io.reactivex.Single
import javax.inject.Inject

interface StationsRemoteDataSource {

    fun getStationsData(bounds: LatLngBounds): Single<List<StationData>>
}

class StationsRemoteDataSourceImpl @Inject constructor(
    private val api: ApiService,
    private val apiStationDataMapper: ApiStationDataMapper
) : StationsRemoteDataSource {

    @SuppressLint("DefaultLocale")
    override fun getStationsData(bounds: LatLngBounds): Single<List<StationData>> {
        return api.getStationsData(bounds.asString())
            .map {
                logInfo("status: ${it.status}. got ${it.data?.size} stations from remote data source")
                when (it.status) {
                    Status.OK.name.toLowerCase() -> it.data?.map { apiStationDataMapper.mapTo(it) }
                    else -> throw InvalidApiResponseException(it.error)
                }
            }
    }
}