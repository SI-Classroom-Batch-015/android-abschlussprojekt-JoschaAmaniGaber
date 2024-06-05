package com.example.rickandmortyguide.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.data.model.Location

@Dao
interface LocationDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<Location>)

    @Query("select * from Location")
    fun getAllLocations(): LiveData<List<Location>>

    @Query("select name from Location")
    fun getLocationName(): LiveData<String>

}