package com.example.rickandmortyguide.data.model.enteties.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.rickandmortyguide.data.model.enteties.Character
import com.example.rickandmortyguide.data.model.enteties.Location

data class LocationWithCharacters(
    @Embedded val location: Location,
    @Relation(
        parentColumn = "name",
        entityColumn = "location_name"
    )
    val characters: List<Character>
)
