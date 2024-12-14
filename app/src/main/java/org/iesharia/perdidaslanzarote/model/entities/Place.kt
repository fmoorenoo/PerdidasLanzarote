package org.iesharia.perdidaslanzarote.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "place",
)

data class Place(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val longitude: String,
    val latitude: String
)
