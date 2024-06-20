package com.example.rickandmortyguide.data.model.api.results

import com.example.rickandmortyguide.data.model.enteties.Episode
import com.example.rickandmortyguide.data.model.api.info.InfoEpisode
import com.squareup.moshi.Json


data class EpisodeResults(

    @Json(name = "info")
    val infoEpisode: InfoEpisode,

    val results: List<Episode>
)
