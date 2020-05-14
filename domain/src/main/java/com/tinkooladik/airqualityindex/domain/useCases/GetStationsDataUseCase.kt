package com.tinkooladik.airqualityindex.domain.useCases

import com.tinkooladik.airqualityindex.domain.ObservableUseCase
import com.tinkooladik.airqualityindex.domain.SchedulersProvider
import com.tinkooladik.airqualityindex.domain.providers.LocationBoundsProvider
import com.tinkooladik.airqualityindex.domain.providers.StationData
import com.tinkooladik.airqualityindex.domain.providers.StationsDataProvider
import io.reactivex.Observable
import javax.inject.Inject

class GetStationsDataUseCase @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val stationsDataProvider: StationsDataProvider,
    private val locationBoundsProvider: LocationBoundsProvider
) : ObservableUseCase<GetStationsDataUseCase.Params, List<StationData>>(schedulersProvider) {

    override fun buildObservable(params: Params): Observable<List<StationData>> {
        return locationBoundsProvider.getBounds((params.radius * 1000).toDouble())
            .subscribeOn(schedulerProvider.ui())
            .observeOn(schedulerProvider.io())
            .flatMapObservable { bounds ->
                stationsDataProvider.getStationsData(bounds)
                    .map { it.filter { it.index >= params.minIndex } }
            }
            .observeOn(schedulerProvider.ui())
    }

    /**
     * @param radius - search radius in km
     */
    data class Params(
        val radius: Int,
        val minIndex: Int
    )
}