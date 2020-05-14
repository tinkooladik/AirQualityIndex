package com.tinkooladik.airqualityindex.data.stations

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class StationsDao {

    @Insert
    abstract fun insert(data: List<RoomStationData>): Completable

    @Delete
    abstract fun delete(data: List<RoomStationData>): Completable

    @Query("SELECT * FROM station_data WHERE (lat BETWEEN :lat1 AND :lat2) AND (lng BETWEEN :lng1 AND :lng2)")
    abstract fun getAll(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Single<List<RoomStationData>>
}