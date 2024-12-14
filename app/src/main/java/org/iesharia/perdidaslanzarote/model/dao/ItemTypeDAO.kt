package org.iesharia.perdidaslanzarote.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import org.iesharia.perdidaslanzarote.model.entities.ItemType

@Dao
interface ItemTypeDao {
    @Query("SELECT * FROM item_type")
    fun getAllItemTypes(): List<ItemType>

    @Insert
    suspend fun insertItemType(itemType: ItemType)
}