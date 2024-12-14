package org.iesharia.perdidaslanzarote.model.dao

import androidx.room.Dao
import androidx.room.Insert
import org.iesharia.perdidaslanzarote.model.entities.LostItem

@Dao
interface LostItemDao {
    @Insert
    suspend fun insertLostItem(lostItem: LostItem)
}