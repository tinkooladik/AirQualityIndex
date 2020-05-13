package com.tinkooladik.airqualityindex.domain


import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

@Suppress("UnstableApiUsage")
abstract class SingleUseCase<ParamType : Any, ResultType : Any>(private val schedulerProvider: SchedulersProvider) {

    private val subscription = CompositeDisposable()
        get() = if (field.isDisposed) CompositeDisposable() else field

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

    fun unsubscribe() {
        subscription.dispose()
    }
}