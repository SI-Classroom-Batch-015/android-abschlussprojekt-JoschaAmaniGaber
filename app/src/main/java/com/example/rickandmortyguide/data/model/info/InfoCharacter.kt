package com.example.rickandmortyguide.data.model.info

import com.squareup.moshi.Json

data class InfoCharacter(
    val count: Int,
    val pages: Int,

    @Json(name = "next")
    val nextPage: String?,

    @Json(name = "prev")
    val previousPage: String?
)
