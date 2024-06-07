package com.example.rickandmortyguide.charcacter.model

import com.example.rickandmortyguide.main.model.info.Info

data class CharacterResults(
    var info: Info,
    var results: List<Character>
)
