package com.example.rickandmortyguide.main.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyguide.charcacter.model.Character
import com.example.rickandmortyguide.episode.model.Episode
import com.example.rickandmortyguide.location.model.Location

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