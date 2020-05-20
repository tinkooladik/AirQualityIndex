package com.tinkooladik.airqualityindex.data

interface DateFormatProvider {

    fun parse(source: String?): Long
}