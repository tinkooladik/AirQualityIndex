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
            }.switchMap { stations ->
                Observable.fromIterable(stations)
                    .filter { it.index >= params.minIndex }
                    .toSortedList(StationsComparator)
                    .toObservable()
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

    object StationsComparator : Comparator<StationData> {
        override fun compare(p0: StationData, p1: StationData): Int {
            val index = p0.index.compareTo(p1.index)
            if (index != 0) return index
            return p0.id.compareTo(p1.id)
        }

    }
}