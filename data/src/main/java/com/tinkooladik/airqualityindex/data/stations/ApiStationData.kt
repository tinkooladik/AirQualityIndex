package com.tinkooladik.airqualityindex.data.stations

import com.tinkooladik.airqualityindex.data.DateFormatProvider
import com.tinkooladik.airqualityindex.domain.MapperTo
import com.tinkooladik.airqualityindex.domain.providers.StationData
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class ApiStationResponse(
    val lat: Double,
    val lon: Double,
    val uid: Int,
    val aqi: String,
    val station: ApiStation
)

@Serializable
data class ApiStation(
    val name: String,
    val time: String
)

class ApiStationDataMapper @Inject constructor(
    private val dateFormatProvider: DateFormatProvider
) : MapperTo<ApiStationResponse, StationData> {

    override fun mapTo(item: ApiStationResponse): StationData =
        StationData(
            id = item.uid,
            name = item.station.name,
            lat = item.lat,
            lng = item.lon,
            index = item.aqi.toIntOrNull() ?: -1,
            lastUpdated = dateFormatProvider.parse(item.station.time)
        )
}