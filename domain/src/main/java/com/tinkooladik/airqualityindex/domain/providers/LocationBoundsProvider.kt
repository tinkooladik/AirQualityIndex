package com.tinkooladik.airqualityindex.domain.providers

import io.reactivex.Single

interface LocationBoundsProvider {
    fun getBounds(radius: Double): Single<LatLngBounds>
}