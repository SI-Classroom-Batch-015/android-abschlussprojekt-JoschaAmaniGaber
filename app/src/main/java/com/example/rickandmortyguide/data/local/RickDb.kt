package com.example.rickandmortyguide.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyguide.data.model.entities.Character
import com.example.rickandmortyguide.data.model.entities.Episode
import com.example.rickandmortyguide.data.model.location.Location

@Database(entities = [Character::class, Episode::class, Location::class], version = 1)
abstract class RickDb : RoomDatabase() {

    abstract val dao: RickDbDao

}

private lateinit var INSTANCE: RickDb

fun getRickDb(context: Context) : RickDb {

    synchronized(RickDb::class.java) {
        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                RickDb::class.java,
                "rick_db"
            ).build()
        }
        return INSTANCE
    }
}