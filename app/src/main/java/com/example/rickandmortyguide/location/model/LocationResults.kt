package com.example.rickandmortyguide.location.model

import com.example.rickandmortyguide.main.model.info.Info

data class LocationResults(
    val info: Info,
    val results: List<Location>
)
