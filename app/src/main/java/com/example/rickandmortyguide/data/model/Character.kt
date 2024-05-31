package com.example.rickandmortyguide.data.model


data class Character(
    val id: Long = 0,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: CharacterLocation,
    val image: String,
    val episode: CharacterEpisode,
    val url: String,
    val created: String
)
