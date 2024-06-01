package com.example.rickandmortyguide.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyguide.data.model.Character

@Dao
interface CharacterDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Query("select * from Character")
    fun getAll(): LiveData<List<Character>>

    @Query("select * from Character where id = :key")
    fun getCharacterById(key: Int) : LiveData<Character>

    @Query("delete from Character")
    suspend fun deleteAll()
}
