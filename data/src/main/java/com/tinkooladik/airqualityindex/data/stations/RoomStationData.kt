package com.tinkooladik.airqualityindex.data.stations

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tinkooladik.airqualityindex.domain.MapperFrom
import com.tinkooladik.airqualityindex.domain.MapperTo
import com.tinkooladik.airqualityindex.domain.providers.StationData
import javax.inject.Inject

@Entity(tableName = "station_data")
data class RoomStationData(
    @PrimaryKey val id: Int,
    val name: String,
    val lat: Double,
    val lng: Double,
    val index: Int,
    val lastUpdated: Long
)

class LocalStationDataMapper @Inject constructor() : MapperTo<StationData, RoomStationData>,
    MapperFrom<RoomStationData, StationData> {

    override fun mapTo(item: StationData): RoomStationData =
        RoomStationData(
            id = item.id,
            name = item.name,
            lat = item.lat,
            lng = item.lng,
            index = item.index,
            lastUpdated = item.lastUpdated
        )

    override fun mapFrom(item: RoomStationData): StationData =
        StationData(
            id = item.id,
            name = item.name,
            lat = item.lat,
            lng = item.lng,
            index = item.index,
            lastUpdated = item.lastUpdated
        )
}