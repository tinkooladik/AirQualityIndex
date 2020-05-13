package com.tinkooladik.airqualityindex.domain

class UnknownException : Exception()

class ResourceLoadingFailedException : Exception()

class UnknownViewModel(message: String) : Exception(message)