package org.iesharia.perdidaslanzarote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.iesharia.perdidaslanzarote.model.dao.*
import org.iesharia.perdidaslanzarote.model.entities.*


class AppViewModel(
    private val lostItemDao: LostItemDao,
    private val itemTypeDao: ItemTypeDao,
    private val placeDao: PlaceDao
) : ViewModel() {

    // Añadir una nueva pérdida
    fun addLostItem(itemName: String, itemTypeId: Int, description: String?, placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newLostItem = LostItem(
                itemName = itemName,
                itemTypeId = itemTypeId,
                description = description,
                placeId = placeId
            )
            lostItemDao.insertLostItem(newLostItem)
        }
    }

    fun getItemTypes(): Flow<List<ItemType>> {
        return itemTypeDao.getAllItemTypes()
    }

    fun getPlaces(): Flow<List<Place>> {
        return placeDao.getAllPlaces()
    }

    fun getPlacesWithLostItems(): Flow<List<Place>> {
        return placeDao.getAllPlaces().map { places ->
            places.filter { place ->
                val lostItems = lostItemDao.getLostItemsByPlaceId(place.id)
                lostItems.isNotEmpty()
            }
        }
    }
}