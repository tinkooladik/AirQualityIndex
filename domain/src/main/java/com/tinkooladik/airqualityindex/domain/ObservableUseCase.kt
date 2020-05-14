package com.tinkooladik.airqualityindex.domain

import io.reactivex.Observable

@Suppress("UnstableApiUsage")
abstract class ObservableUseCase<ParamType : Any, ResultType : Any>(schedulerProvider: SchedulersProvider) :
    UseCase(schedulerProvider) {

    protected abstract fun buildObservable(params: ParamType): Observable<ResultType>

    fun execute(
        params: ParamType,
        onNext: (ResultType) -> Unit,
        onError: (Throwable) -> Unit = { },
        onComplete: () -> Unit = {}
    ) {
        val item = buildObservable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnEach { onComplete() }
            .subscribe({ onNext(it) }, { onError(it) })
        subscription.add(item)
    }

    fun <NewType> execute(
        params: ParamType,
        mapper: MapperTo<ResultType, NewType>,
        onNext: (NewType) -> Unit,
        onError: (Throwable) -> Unit = { },
        onComplete: () -> Unit = {}
    ) {
        val item = buildObservable(params)
            .map { mapper.mapTo(it) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnEach { onComplete() }
            .subscribe({ onNext(it) }, { onError(it) })
        subscription.add(item)
    }
}