package com.tinkooladik.airqualityindex.presentation.home

import com.tinkooladik.airqualityindex.common.adapter.ObservableListItem
import com.tinkooladik.airqualityindex.domain.MapperTo
import com.tinkooladik.airqualityindex.domain.providers.StationData
import javax.inject.Inject

class StationVM : ObservableListItem() {
    var name: String? = null
    var id: Int? = null
    var index: Int? = null
    var lat: Double? = null
    var lng: Double? = null
    var lastUpdated: Long? = null
    var city: String? = null

    override fun isTheSameItem(other: Any?): Boolean {
        return other is StationVM && id == other.id
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