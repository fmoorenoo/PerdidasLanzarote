package org.iesharia.perdidaslanzarote.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "lost_item"
)

data class LostItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val description: String? = null,
    val placeId: Int,
    val itemTypeId: Int
)
