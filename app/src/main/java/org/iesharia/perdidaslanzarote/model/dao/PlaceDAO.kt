package org.iesharia.perdidaslanzarote.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.iesharia.perdidaslanzarote.model.entities.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun getAllPlaces(): Flow<List<Place>>

    @Insert
    suspend fun insertPlace(place: Place)

    @Query("SELECT * FROM place WHERE id = :placeId")
    suspend fun getPlaceById(placeId: Int): Place
}