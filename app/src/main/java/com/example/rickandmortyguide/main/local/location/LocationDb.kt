package com.example.rickandmortyguide.main.local.location

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyguide.location.model.Location


@Database(entities = [Location::class], version = 1)
abstract class LocationDb : RoomDatabase() {

    abstract val dao: LocationDbDao

}

private lateinit var INSTANCE: LocationDb

fun getLocationDb(context: Context) : LocationDb {

    synchronized(LocationDb::class.java) {
        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                LocationDb::class.java,
                "location_db"
            ).build()
        }
        return INSTANCE
    }
}