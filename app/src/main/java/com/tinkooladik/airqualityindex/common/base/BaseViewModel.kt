package com.tinkooladik.airqualityindex.common.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tinkooladik.airqualityindex.common.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel : ViewModel() {

    var loading: ObservableBoolean = ObservableBoolean(false)

    val error: LiveData<Throwable>
        get() = _error

    protected val _error = SingleLiveEvent<Throwable>()

    protected val permissionsGranted by lazy {
        BehaviorSubject.createDefault(false)
    }

    private var disposables: CompositeDisposable = CompositeDisposable()

    open fun initViewModel() {}

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun permissionsGranted(granted: Boolean) {
        permissionsGranted.onNext(granted)
    }

    protected fun Disposable.disposeOnClear() = disposables.add(this)
}