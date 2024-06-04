package com.example.rickandmortyguide.data.model

import com.squareup.moshi.Json

data class Info(
    val count: Int,
    val pages: Int,

    @Json(name = "next")
    val nextPage: String?,

    @Json(name = "prev")
    val previousPage: String?
)
