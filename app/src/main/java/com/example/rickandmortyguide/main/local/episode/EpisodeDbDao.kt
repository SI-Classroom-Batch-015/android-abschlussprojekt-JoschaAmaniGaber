package com.example.rickandmortyguide.main.local.episode

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyguide.episode.model.Episode

@Dao
interface EpisodeDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<Episode>)

    @Query("select * from Episode")
    fun getAllEpisodes(): LiveData<List<Episode>>

    @Query("delete from Episode")
    suspend fun deleteAll()
}