package com.example.rickandmortyguide.data.model.character

import com.example.rickandmortyguide.data.model.entities.Character
import com.example.rickandmortyguide.data.model.info.InfoCharacter
import com.example.rickandmortyguide.data.model.info.InfoLocation
import com.squareup.moshi.Json

data class CharacterResults(

    @Json(name = "info")
    var infoCharacter: InfoCharacter,

    var results: List<Character>
)
