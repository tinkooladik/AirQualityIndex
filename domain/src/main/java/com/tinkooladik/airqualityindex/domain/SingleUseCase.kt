package com.tinkooladik.airqualityindex.domain

import io.reactivex.Single

@Suppress("UnstableApiUsage")
abstract class SingleUseCase<ParamType : Any, ResultType : Any>(schedulerProvider: SchedulersProvider) :
    UseCase(schedulerProvider) {

    protected abstract fun buildSingle(params: ParamType?): Single<ResultType>

    fun execute(
        params: ParamType? = null,
        onNext: (ResultType) -> Unit,
        onError: (Throwable) -> Unit = { },
        onComplete: () -> Unit = {}
    ) {
        val item = buildSingle(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnTerminate(onComplete)
            .subscribe({ onNext(it) }, { onError(it) })
        subscription.add(item)
    }

    fun <NewType> execute(
        params: ParamType?,
        mapper: MapperTo<ResultType, NewType>,
        onNext: (NewType) -> Unit,
        onError: (Throwable) -> Unit = { },
        onComplete: () -> Unit = {}
    ) {
        val item = buildSingle(params)
            .map { mapper.mapTo(it) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnTerminate(onComplete)
            .subscribe({ onNext(it) }, { onError(it) })
        subscription.add(item)
    }
}