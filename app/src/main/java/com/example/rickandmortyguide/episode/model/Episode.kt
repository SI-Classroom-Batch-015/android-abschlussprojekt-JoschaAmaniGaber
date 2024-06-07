package com.example.rickandmortyguide.episode.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episode(
    @PrimaryKey
    val id: Int,
    val name: String,
    val air_date: String,
    // val characters: List<String>
)
