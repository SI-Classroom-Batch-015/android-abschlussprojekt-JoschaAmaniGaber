package com.example.rickandmortyguide.data.model.enteties

import androidx.room.Entity

@Entity
data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin_name: String,
    val location_name: String,
    val image: String,
    val url: String,
    val created: String,
    // val episode: List<String>,
)
