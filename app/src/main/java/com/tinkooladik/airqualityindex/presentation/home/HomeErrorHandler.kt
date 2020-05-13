package com.tinkooladik.airqualityindex.presentation.home

import android.content.res.Resources
import com.tinkooladik.airqualityindex.common.message.ErrorHandler

interface HomeErrorHandler : ErrorHandler {

    override fun extractMessage(resources: Resources, error: Throwable?): String? =
        when (error) {

            else -> null
        }
}