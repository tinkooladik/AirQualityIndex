package com.tinkooladik.airqualityindex.data

import com.tinkooladik.airqualityindex.data.stations.ApiStationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("map/bounds/")
    fun getStationsData(@Query("latlng") latLng: String): Single<ApiResponse<List<ApiStationResponse>>>
}