package org.iesharia.perdidaslanzarote.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_type"
)

data class ItemType(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val numItems: Int
)
