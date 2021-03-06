package com.tinkooladik.airqualityindex.domain

class UnknownException : Exception()

class DateFormatException : Exception()

class InvalidApiResponseException(message: String? = null) : Exception(message)

class UnknownViewModel(message: String) : Exception(message)

class NoLocationException : Exception()

class NoSuchValueException : Exception()