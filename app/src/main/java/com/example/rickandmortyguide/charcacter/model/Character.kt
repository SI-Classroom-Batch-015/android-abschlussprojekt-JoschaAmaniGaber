package com.example.rickandmortyguide.charcacter.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.net.URI


@Entity
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,

    @Json(name = "origin")
    @Ignore val _origin: Origin? = null,
    val origin: Int,

    @Ignore val location: List<String>,
    val image: String,

    @Ignore val episode: List<String>? = null,
    val url: String,
    val created: String
) {
    constructor(
        id: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String,
        _origin: Origin,
        image: String,
        url: String,
        created: String
    ) : this(
        id,
        name,
        status,
        species,
        type,
        gender,
        null,
        extractIdFromLocationUrl(_origin.url),
        image,
        url,
        created
    )

}

fun extractIdFromLocationUrl(url: String): Int {
    val uri = URI(url)
    val path = uri.path
    val idStr = path.substring(path.lastIndexOf('/') + 1)
    val id = idStr.toInt()

    return id
}