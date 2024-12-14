package org.iesharia.perdidaslanzarote.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "lost_item",
    foreignKeys = [
        ForeignKey(
            entity = Place::class,
            // Referencia en la tabla Place
            parentColumns = ["id"],
            // Referencia en esta tabla
            childColumns = ["placeId"],
        ),
        ForeignKey(
            entity = ItemType::class,
            // Referencia en la tabla ItemType
            parentColumns = ["id"],
            // Referencia en esta tabla
            childColumns = ["itemTypeId"],
        )
    ]
)

data class LostItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val itemTypeId: Int,
    val description: String? = null,
    val placeId: Int
)
