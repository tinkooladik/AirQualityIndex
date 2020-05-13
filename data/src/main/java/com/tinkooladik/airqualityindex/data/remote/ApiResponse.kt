package com.tinkooladik.airqualityindex.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class Status { OK, ERROR }

@Serializable
data class ApiResponse<T>(
    val status: String,
    @SerialName("message") val error: String? = null,
    val data: T? = null
)