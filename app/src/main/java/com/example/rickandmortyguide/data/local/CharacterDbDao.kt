package com.example.rickandmortyguide.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyguide.data.model.Character

@Dao
interface CharacterDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<Character>)

    @Query("select * from Character")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("delete from Character")
    suspend fun deleteAll()

}
