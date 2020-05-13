package com.tinkooladik.airqualityindex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tinkooladik.airqualityindex.data.stations.RoomStationData
import com.tinkooladik.airqualityindex.data.stations.StationsDao

@Database(
    entities = [
        RoomStationData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationsDao(): StationsDao
}
