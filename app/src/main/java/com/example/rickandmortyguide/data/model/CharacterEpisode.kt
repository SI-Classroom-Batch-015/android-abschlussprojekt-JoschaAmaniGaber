package com.example.rickandmortyguide.data.model

import com.squareup.moshi.Json

data class CharacterEpisode(

    @Json(name = "episode")
    val episodes: List<String>

)
