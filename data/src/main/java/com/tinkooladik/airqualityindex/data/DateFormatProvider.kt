package com.tinkooladik.airqualityindex.data

import com.tinkooladik.airqualityindex.domain.DateFormatException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateFormatProvider @Inject constructor() {

    private val sdfTimeZone = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())

    fun parse(source: String?): Long {
        if (source == null) throw DateFormatException()
        try {
            return sdfTimeZone.parse(source)!!.time
        } catch (e: Exception) {
            throw DateFormatException()
        }
    }
}