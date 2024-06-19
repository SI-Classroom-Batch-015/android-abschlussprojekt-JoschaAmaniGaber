package com.example.rickandmortyguide.data.model.character

import android.view.ViewDebug.IntToString
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
//    val origin: Origin,
//    val location: CharacterLocation,
    val image: String,
//    val episode: CharacterEpisode,
    val url: String,
    val created: String
)
