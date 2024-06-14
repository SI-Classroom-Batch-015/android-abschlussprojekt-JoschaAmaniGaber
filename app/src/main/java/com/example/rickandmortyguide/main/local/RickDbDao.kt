package com.example.rickandmortyguide.main.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyguide.charcacter.model.Character
import com.example.rickandmortyguide.charcacter.model.CharacterLocationCrossRef
import com.example.rickandmortyguide.charcacter.model.CharacterWithLocations
import com.example.rickandmortyguide.episode.model.Episode
import com.example.rickandmortyguide.location.model.Location

@Dao
interface RickDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<Location>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterLocationCrossRef(characterLocationCrossRef: CharacterLocationCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<Character>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<Episode>)

    @Query("select * from Character")
    fun getAllCharacters(): LiveData<List<CharacterWithLocations>>

    @Query("select * from Episode")
    fun getAllEpisodes(): LiveData<List<Episode>>

    @Query("select * from Location")
    fun getAllLocations(): LiveData<List<Location>>

    @Query("select name from Location")
    fun getLocationName(): LiveData<String>

    @Query("delete from Character")
    suspend fun deleteAllCharacters()

    @Query("delete from Episode")
    suspend fun deleteAllEpisodes()
}
