package com.tinkooladik.airqualityindex.domain


import io.reactivex.disposables.CompositeDisposable

@Suppress("UnstableApiUsage")
abstract class UseCase(protected val schedulerProvider: SchedulersProvider) {

    protected val subscription = CompositeDisposable()
        get() = if (field.isDisposed) CompositeDisposable() else field

    fun unsubscribe() {
        subscription.dispose()
    }
}