package com.tinkooladik.airqualityindex.presentation.home

import android.content.res.Resources
import com.tinkooladik.airqualityindex.R
import com.tinkooladik.airqualityindex.common.message.ErrorHandler
import com.tinkooladik.airqualityindex.domain.InvalidApiResponseException
import com.tinkooladik.airqualityindex.domain.NoLocationException
import com.tinkooladik.airqualityindex.domain.NoSuchValueException

interface HomeErrorHandler : ErrorHandler {

    override fun extractMessage(resources: Resources, error: Throwable?): String? =
        when (error) {
            is NoLocationException -> resources.getString(R.string.error_no_location)
            is InvalidApiResponseException -> error.message
            is NoSuchValueException -> resources.getString(R.string.error_no_such_element)
            else -> null
        }
}