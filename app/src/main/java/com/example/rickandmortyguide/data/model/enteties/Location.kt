package com.example.rickandmortyguide.data.model.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey(autoGenerate = false)
    val location_name: String,
    val id: Int,
    val type: String,
    val dimension: String,
//    val residents: List<String>
)
