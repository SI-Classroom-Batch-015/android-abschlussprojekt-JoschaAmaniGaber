package com.example.rickandmortyguide.episode.model

import com.example.rickandmortyguide.main.model.info.Info


data class EpisodeResults(
    val info: Info,
    val results: List<Episode>
)
