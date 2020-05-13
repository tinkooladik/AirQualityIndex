package com.tinkooladik.airqualityindex.presentation.home

import androidx.databinding.BaseObservable
import com.tinkooladik.airqualityindex.domain.MapperTo
import com.tinkooladik.airqualityindex.domain.providers.StationData
import javax.inject.Inject

class HomeStationVM : BaseObservable() {
    var name: String? = null
    var id: Int? = null
    var index: Int? = null
}

class HomeStationVmMapper @Inject constructor() : MapperTo<List<StationData>, List<HomeStationVM>> {

    override fun mapTo(item: List<StationData>): List<HomeStationVM> =
        item.map { mapItem(it) }

    private fun mapItem(item: StationData): HomeStationVM =
        HomeStationVM().apply {
            id = item.id
            name = item.name
            index = item.index
        }
}