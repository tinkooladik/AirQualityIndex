package com.tinkooladik.airqualityindex.data.stations

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
abstract class StationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(data: List<RoomStationData>): Completable

    @Delete
    abstract fun delete(data: List<RoomStationData>): Completable

    @Query("SELECT * FROM station_data WHERE (lat BETWEEN :lat1 AND :lat2) AND (lng BETWEEN :lng1 AND :lng2)")
    abstract fun getAllInBounds(
        lat1: Double,
        lng1: Double,
        lat2: Double,
        lng2: Double
    ): Maybe<List<RoomStationData>>

    @Query("SELECT * FROM station_data WHERE id = :id")
    abstract fun getById(id: Int): Maybe<RoomStationData>
}