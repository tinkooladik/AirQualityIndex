package com.tinkooladik.airqualityindex.data

import timber.log.Timber


fun Any.log(message: String, throwable: Throwable? = null, tag: String? = null) {
    Timber.tag(tag ?: this.javaClass.simpleName).run {
        if (throwable == null) d(message)
        else d(throwable, message)
    }
}

fun Any.logError(message: String, throwable: Throwable? = null, tag: String? = null) {
    Timber.tag(tag ?: this.javaClass.simpleName).run {
        if (throwable == null) e(message)
        else e(throwable, message)
    }
}

fun Any.logWarn(message: String, throwable: Throwable? = null, tag: String? = null) {
    Timber.tag(tag ?: this.javaClass.simpleName).run {
        if (throwable == null) w(message)
        else w(throwable, message)
    }
}

fun Any.logInfo(message: String, tag: String? = null) {
    Timber.tag(tag ?: this.javaClass.simpleName).i(message)
}