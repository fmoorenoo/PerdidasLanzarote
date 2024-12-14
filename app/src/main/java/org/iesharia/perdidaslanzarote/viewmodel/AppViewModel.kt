package org.iesharia.perdidaslanzarote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    fun addItemType() {
        viewModelScope.launch(Dispatchers.IO) {
            val newItemType = ItemType(name = "")
            itemTypeDao.insertItemType(newItemType)
        }
    }

    fun addPlace() {
        viewModelScope.launch(Dispatchers.IO) {
            val newPlace = Place(name = "", latitude = "", longitude = "")
            placeDao.insertPlace(newPlace)
        }
    }

    fun getItemTypes(): Flow<List<ItemType>> {
        return itemTypeDao.getAllItemTypes()
    }

    fun getPlaces(): Flow<List<Place>> {
        return placeDao.getAllPlaces()
    }
}