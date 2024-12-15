package org.iesharia.perdidaslanzarote.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.iesharia.perdidaslanzarote.model.entities.LostItem

@Dao
interface LostItemDao {
    @Insert
    suspend fun insertLostItem(lostItem: LostItem)

    @Query("SELECT * FROM lost_item WHERE placeId = :placeId")
    suspend fun getLostItemsByPlaceId(placeId: Int): List<LostItem>

    @Query("SELECT COUNT(*) FROM lost_item WHERE placeId = :placeId")
    suspend fun getLostItemsCountByPlaceId(placeId: Int): Int

}