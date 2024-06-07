package com.example.rickandmortyguide.main.local.episode

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyguide.episode.model.Episode

@Database(entities = [Episode::class], version = 1)
abstract class EpisodeDb : RoomDatabase() {

    abstract val dao: EpisodeDbDao

}

private lateinit var INSTANCE: EpisodeDb

fun getEpisodesDb(context: Context) : EpisodeDb {

    synchronized(EpisodeDb::class.java) {
        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                EpisodeDb::class.java,
                "episode_db"
            ).build()
        }
        return INSTANCE
    }
}