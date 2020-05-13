package com.tinkooladik.airqualityindex.data

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

internal val nonstrictJson = Json(JsonConfiguration.Stable.copy(strictMode = false))
