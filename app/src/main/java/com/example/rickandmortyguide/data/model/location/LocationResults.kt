package com.example.rickandmortyguide.data.model.location

import com.example.rickandmortyguide.data.model.info.InfoLocation
import com.squareup.moshi.Json

data class LocationResults(

    @Json(name = "info")
    val infoLocation: InfoLocation,

    val results: List<Location>
)
