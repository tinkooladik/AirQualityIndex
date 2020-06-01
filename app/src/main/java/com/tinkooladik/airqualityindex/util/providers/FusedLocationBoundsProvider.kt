package com.tinkooladik.airqualityindex.util.providers

import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tinkooladik.airqualityindex.domain.NoLocationException
import com.tinkooladik.airqualityindex.domain.providers.LatLng
import com.tinkooladik.airqualityindex.domain.providers.LatLngBounds
import com.tinkooladik.airqualityindex.domain.providers.LocationBoundsProvider
import com.tinkooladik.airqualityindex.util.computeOffset
import io.reactivex.Single
import javax.inject.Inject
import kotlin.math.sqrt


class FusedLocationBoundsProvider @Inject constructor(context: Context) : LocationBoundsProvider {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    override fun getBounds(radius: Double): Single<LatLngBounds> =
        getLocation()
            .map { location ->
                toBounds(location.toLatLng(), radius)
            }

    private fun getLocation(): Single<Location> =
        Single.defer {
            Single.create<Location> { emitter ->
                fusedLocationClient.lastLocation
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful && task.result != null) {
                            emitter.onSuccess(task.result as Location)
                        } else {
                            emitter.onError(NoLocationException())
                        }
                    }
            }
        }

    private fun toBounds(center: LatLng, radiusInMeters: Double): LatLngBounds? {
        val distanceFromCenterToCorner = radiusInMeters * sqrt(2.0)
        val southwestCorner: LatLng =
            center.computeOffset(distanceFromCenterToCorner, 225.0)
        val northeastCorner: LatLng =
            center.computeOffset(distanceFromCenterToCorner, 45.0)
        return LatLngBounds(southwestCorner, northeastCorner)
    }

    private fun Location.toLatLng() = LatLng(latitude, longitude)
}