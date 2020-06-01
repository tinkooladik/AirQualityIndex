package com.tinkooladik.airqualityindex.util.providers

import com.tinkooladik.airqualityindex.data.DateFormatProvider
import com.tinkooladik.airqualityindex.domain.DateFormatException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateFormatProviderImpl @Inject constructor() : DateFormatProvider {

    private val sdfTimeZone = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())

    override fun parse(source: String?): Long {
        if (source == null) throw DateFormatException()
        try {
            return sdfTimeZone.parse(source)!!.time
        } catch (e: Exception) {
            throw DateFormatException()
        }
    }
}