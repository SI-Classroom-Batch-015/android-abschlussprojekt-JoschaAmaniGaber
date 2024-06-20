package com.example.rickandmortyguide.data.model.api


data class CharacterFromAPI(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Origin,
    val image: String,
    val url: String,
    val created: String,
    val episode: List<String>,
)
