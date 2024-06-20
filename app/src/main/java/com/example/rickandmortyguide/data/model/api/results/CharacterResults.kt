package com.example.rickandmortyguide.data.model.api.results

import com.example.rickandmortyguide.data.model.api.CharacterFromAPI
import com.example.rickandmortyguide.data.model.api.info.InfoCharacter
import com.squareup.moshi.Json

data class CharacterResults(

    @Json(name = "info")
    var infoCharacter: InfoCharacter,

    var results: List<CharacterFromAPI>
)
