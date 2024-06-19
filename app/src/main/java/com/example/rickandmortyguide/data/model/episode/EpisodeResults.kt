package com.example.rickandmortyguide.data.model.episode

import com.example.rickandmortyguide.data.model.info.InfoEpisode
import com.example.rickandmortyguide.data.model.info.InfoLocation
import com.squareup.moshi.Json


data class EpisodeResults(

    @Json(name = "info")
    val infoEpisode: InfoEpisode,

    val results: List<Episode>
)
