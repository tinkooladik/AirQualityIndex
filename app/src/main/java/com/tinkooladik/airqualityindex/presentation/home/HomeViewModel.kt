package com.tinkooladik.airqualityindex.presentation.home

import androidx.lifecycle.LiveData
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.data.logError
import com.tinkooladik.airqualityindex.domain.useCases.GetStationsDataUseCase
import com.tinkooladik.airqualityindex.util.SingleLiveEvent
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

const val DEFAULT_RADIUS = 20
const val DEFAULT_MIN_INDEX = 0

class HomeViewModel @Inject constructor(
    private val getStationsDataUseCase: GetStationsDataUseCase,
    private val stationsListVmMapper: StationsListVmMapper
) : BaseViewModel() {

    val items: LiveData<List<StationVM>>
        get() = _items

    val radiusValues = arrayListOf(5, 10, 20, 50, 100)
    val minIndexValues = arrayListOf(0, 5, 10, 20, 50, 100, 200)

    val currentRadius: String?
        get() = radiusSubject.value.toString()

    val currentMinIndex: String?
        get() = minIndexSubject.value.toString()

    private var radiusSubject = BehaviorSubject.createDefault(DEFAULT_RADIUS)
    private var minIndexSubject = BehaviorSubject.createDefault(DEFAULT_MIN_INDEX)

    private val _items = SingleLiveEvent<List<StationVM>>()

    override fun initViewModel() {
        Observables.combineLatest(
            permissionsGranted,
            radiusSubject.distinctUntilChanged(),
            minIndexSubject.distinctUntilChanged()
        ) { permission, radius, index -> if (permission) loadStations(radius, index) }
            .subscribe()
            .disposeOnClear()
    }

    //todo use binding adapter instead
    fun selectRadius(pos: Int) {
        radiusSubject.onNext(radiusValues[pos])
    }

    //todo use binding adapter instead
    fun selectMinIndex(pos: Int) {
        minIndexSubject.onNext(minIndexValues[pos])
    }

    private fun loadStations(radius: Int, minIndex: Int) {
        loading.set(true)
        getStationsDataUseCase.execute(
            GetStationsDataUseCase.Params(radius, minIndex),
            mapper = stationsListVmMapper,
            onNext = { _items.value = it },
            onError = {
                _error.value = it
                logError("failed to load stations data", it)
            },
            onComplete = { loading.set(false) })
    }
}