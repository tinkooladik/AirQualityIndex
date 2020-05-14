package com.tinkooladik.airqualityindex.domain.useCases

import com.tinkooladik.airqualityindex.domain.SchedulersProvider
import com.tinkooladik.airqualityindex.domain.SingleUseCase
import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Single
import javax.inject.Inject

class GetStationDetailsUseCase @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val stationsDataProvider: StationsDataProvider
) : SingleUseCase<Int, StationData>(schedulersProvider) {

    override fun buildSingle(params: Int): Single<StationData> {
        //todo load some extra info from api
        return stationsDataProvider.getStationById(params).toSingle()
    }
}