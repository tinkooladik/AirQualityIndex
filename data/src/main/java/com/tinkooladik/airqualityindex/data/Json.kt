package com.tinkooladik.airqualityindex.data

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

internal val nonstrictJson = Json(configuration = JsonConfiguration.Stable.copy(prettyPrint = true))