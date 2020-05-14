package com.tinkooladik.airqualityindex.util

import com.tinkooladik.airqualityindex.domain.providers.LatLng
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Suppress("NAME_SHADOWING")
fun LatLng.computeOffset(distance: Double, heading: Double): LatLng {
    var distance = distance
    var heading = heading
    distance /= 6371009.0
    heading = Math.toRadians(heading)
    val fromLat = Math.toRadians(lat)
    val fromLng = Math.toRadians(lng)
    val cosDistance = cos(distance)
    val sinDistance = sin(distance)
    val sinFromLat = sin(fromLat)
    val cosFromLat = cos(fromLat)
    val sinLat =
        cosDistance * sinFromLat + sinDistance * cosFromLat * cos(heading)
    val dLng = atan2(
        sinDistance * cosFromLat * sin(heading),
        cosDistance - sinFromLat * sinLat
    )
    return LatLng(
        Math.toDegrees(asin(sinLat)),
        Math.toDegrees(fromLng + dLng)
    )
}