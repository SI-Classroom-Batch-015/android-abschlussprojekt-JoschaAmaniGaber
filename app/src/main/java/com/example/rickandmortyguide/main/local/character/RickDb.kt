package com.example.rickandmortyguide.main.local.character

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyguide.charcacter.model.Character

@Database(entities = [Character::class], version = 1)
abstract class RickDb : RoomDatabase() {

    abstract val dao: RickDbDao

}

private lateinit var INSTANCE: RickDb

fun getCharacterDb(context: Context) : RickDb {

    synchronized(RickDb::class.java) {
        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                RickDb::class.java,
                "character_db"
            ).build()
        }
        return INSTANCE
    }
}