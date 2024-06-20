package com.example.rickandmortyguide.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI

@Entity
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
//    @Ignore val episode: List<String>?,
    val url: String,
    val created: String
)

fun extractIdFromLocationUrl(url: String): String {
    val uri = URI(url)
    val path = uri.path
    val idStr = path.substring(path.lastIndexOf('/') + 1)
    val id = idStr

    return id
}


//{
//
//    constructor(
//        id: Int,
//        name: String,
//        status: String,
//        species: String,
//        type: String,
//        gender: String,
//        origin: Origin?,
//        location: Origin?,
//        image: String,
//        url: String,
//        created: String
//    ) : this(
//        id,
//        name,
//        status,
//        species,
//        type,
//        gender,
//        null,
//        extractIdFromLocationUrl(origin?.url ?:"this is an error message"),
//        null,
//        extractIdFromLocationUrl(location?.url ?:"this is an error message"),
//        image,
//        url,
//        created
//    ) {}
//}

