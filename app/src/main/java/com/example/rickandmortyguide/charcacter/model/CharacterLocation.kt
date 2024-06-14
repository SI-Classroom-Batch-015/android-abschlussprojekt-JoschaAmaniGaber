package com.example.rickandmortyguide.charcacter.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.rickandmortyguide.location.model.Location

@Entity(primaryKeys = ["characterId", "locationId"])
data class CharacterLocationCrossRef(
    val characterId: Int,
    val locationId: Int
)

data class CharacterWithLocations(
    @Embedded
    val character: Character,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(CharacterLocationCrossRef::class)
    )
    val locations: List<Location>,
)