package com.tinkooladik.airqualityindex.presentation.home

import androidx.databinding.BaseObservable
import com.tinkooladik.airqualityindex.domain.MapperTo
import com.tinkooladik.airqualityindex.domain.providers.StationData
import javax.inject.Inject

class StationVM : BaseObservable() {
    var name: String? = null
    var id: Int? = null
    var index: Int? = null
    var lat: Double? = null
    var lng: Double? = null
    var lastUpdated: Long? = null
    var city: String? = null

    override fun equals(other: Any?): Boolean {
        return other is StationVM && id == other.id && index == other.index
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (id ?: 0)
        result = 31 * result + (index ?: 0)
        return result
    }
}

class StationVmMapper @Inject constructor() : MapperTo<StationData, StationVM> {

    override fun mapTo(item: StationData): StationVM =
        StationVM().apply {
            id = item.id
            name = item.name
            index = item.index
            lat = item.lat
            lng = item.lng
            lastUpdated = item.lastUpdated
        }
}

class StationsListVmMapper @Inject constructor(private val itemMapper: StationVmMapper) :
    MapperTo<List<StationData>, List<StationVM>> {

    override fun mapTo(item: List<StationData>): List<StationVM> =
        item.map { itemMapper.mapTo(it) }
}