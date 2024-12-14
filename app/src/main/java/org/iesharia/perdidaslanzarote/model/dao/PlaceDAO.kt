package org.iesharia.perdidaslanzarote.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.iesharia.perdidaslanzarote.model.entities.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun getAllPlaces(): List<Place>

    @Insert
    suspend fun insertPlace(place: Place)
}