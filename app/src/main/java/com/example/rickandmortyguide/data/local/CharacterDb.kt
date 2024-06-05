package com.example.rickandmortyguide.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyguide.data.model.Character

@Database(entities = [Character::class], version = 1)
abstract class CharacterDb : RoomDatabase() {

    abstract val dao: CharacterDbDao

}

private lateinit var INSTANCE: CharacterDb

fun getCharacterDb(context: Context) : CharacterDb {

    synchronized(CharacterDb::class.java) {
        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CharacterDb::class.java,
                "character_db"
            ).build()
        }
        return INSTANCE
    }
}