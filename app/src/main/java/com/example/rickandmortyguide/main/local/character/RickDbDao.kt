package com.example.rickandmortyguide.main.local.character

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyguide.charcacter.model.Character

@Dao
interface RickDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<Character>)

    @Query("select * from Character")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("delete from Character")
    suspend fun deleteAll()

}
