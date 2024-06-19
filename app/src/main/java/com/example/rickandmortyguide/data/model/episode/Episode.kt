package com.example.rickandmortyguide.data.model.episode

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episode(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val air_date: String,
    // val characters: List<String>
)
