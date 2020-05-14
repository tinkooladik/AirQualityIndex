package com.tinkooladik.airqualityindex.presentation.home

import androidx.lifecycle.LiveData
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.data.logError
import com.tinkooladik.airqualityindex.domain.useCases.GetStationsDataUseCase
import com.tinkooladik.airqualityindex.util.SingleLiveEvent
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val getStationsDataUseCase: GetStationsDataUseCase,
    private val homeStationVmMapper: HomeStationVmMapper
) : BaseViewModel() {

    val items: LiveData<List<HomeStationVM>>
        get() = _items

    val radiusValues = arrayListOf(5, 10, 20, 50, 100)
    val minIndexValues = arrayListOf(0, 5, 10, 20, 50, 100, 200)

    private var radiusSubject = BehaviorSubject.createDefault(DEFAULT_RADIUS)
    private var minIndexSubject = BehaviorSubject.createDefault(DEFAULT_MIN_INDEX)

    private val _items = SingleLiveEvent<List<HomeStationVM>>()

    fun start() {
        Observables.combineLatest(
            radiusSubject.distinctUntilChanged(),
            minIndexSubject.distinctUntilChanged()
        ) { r, i -> loadStations(r, i) }
            .subscribe()
            .disposeOnClear()
    }

    private fun loadStations(radius: Int, minIndex: Int) {
        logError("loadStations $radius, $minIndex")
        loading.set(true)
        getStationsDataUseCase.execute(
            GetStationsDataUseCase.Params(radius, minIndex),
            mapper = homeStationVmMapper,
            onNext = { _items.value = it },
            onError = {
                _error.value = it
                logError("failed to load stations data", it)
            },
            onComplete = { loading.set(false) })
    }

    //todo use binding adapter instead
    fun selectRadius(pos: Int) {
        radiusSubject.onNext(radiusValues[pos])
    }

    //todo use binding adapter instead
    fun selectMinIndex(pos: Int) {
        minIndexSubject.onNext(minIndexValues[pos])
    }

    companion object {
        val DEFAULT_RADIUS = 20
        val DEFAULT_MIN_INDEX = 0
    }
}