package com.example.rickandmortyguide.data.model.results

import com.example.rickandmortyguide.data.model.entities.Episode
import com.example.rickandmortyguide.data.model.info.InfoEpisode
import com.squareup.moshi.Json


data class EpisodeResults(

    @Json(name = "info")
    val infoEpisode: InfoEpisode,

    val results: List<Episode>
)
