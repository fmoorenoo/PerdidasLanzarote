package org.iesharia.perdidaslanzarote.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.iesharia.perdidaslanzarote.model.entities.LostItem

@Dao
interface LostItemDao {
    @Insert
    suspend fun insertLostItem(lostItem: LostItem)

    @Query("SELECT * FROM lost_item")
    fun getAllLostItems(): Flow<List<LostItem>>

    @Update
    suspend fun updateLostItem(lostItem: LostItem)

    @Delete
    suspend fun deleteLostItem(lostItem: LostItem)

    @Query("SELECT * FROM lost_item WHERE placeId = :placeId")
    suspend fun getLostItemsByPlaceId(placeId: Int): List<LostItem>

    @Query("SELECT COUNT(*) FROM lost_item WHERE placeId = :placeId")
    suspend fun getLostItemsCountByPlaceId(placeId: Int): Int

}