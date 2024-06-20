package com.example.rickandmortyguide.data.model.api.results

import com.example.rickandmortyguide.data.model.enteties.Location
import com.example.rickandmortyguide.data.model.api.info.InfoLocation
import com.squareup.moshi.Json

data class LocationResults(

    @Json(name = "info")
    val infoLocation: InfoLocation,

    val results: List<Location>
)
