package com.example.rickandmortyguide.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyguide.data.model.enteties.Character
import com.example.rickandmortyguide.data.model.enteties.Episode
import com.example.rickandmortyguide.data.model.enteties.Location

@Dao
interface RickDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<Character>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocations(locations: List<Location>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<Episode>)

    @Query("select * from Character")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("select * from Episode")
    fun getAllEpisodes(): LiveData<List<Episode>>

    @Query("select * from Location")
    fun getAllLocations(): LiveData<List<Location>>

    @Query("delete from Episode")
    suspend fun deleteAllEpisode()

    @Query("delete from Character")
    suspend fun deleteAllCharacter()

    @Query("select location_name from Location")
    fun getLocationName(): LiveData<String>

}
