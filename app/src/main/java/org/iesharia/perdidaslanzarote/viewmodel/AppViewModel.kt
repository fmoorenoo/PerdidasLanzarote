package org.iesharia.perdidaslanzarote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.iesharia.perdidaslanzarote.model.dao.*
import org.iesharia.perdidaslanzarote.model.entities.*


class AppViewModel(
    private val lostItemDao: LostItemDao,
    private val itemTypeDao: ItemTypeDao,
    private val placeDao: PlaceDao
) : ViewModel() {

    data class Quadruple<A, B, C, D>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D
    )

    // Añadir una nueva pérdida
    fun addLostItem(itemName: String, itemTypeId: Int,contact: String, description: String?, placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newLostItem = LostItem(
                itemName = itemName,
                itemTypeId = itemTypeId,
                contact = contact,
                description = description,
                placeId = placeId
            )
            lostItemDao.insertLostItem(newLostItem)
        }
    }
    fun getAllLostItems(): Flow<List<LostItem>> = lostItemDao.getAllLostItems()

    fun updateLostItem(updatedItem: LostItem) {
        viewModelScope.launch {
            lostItemDao.updateLostItem(updatedItem)
        }
    }

    fun deleteLostItem(itemId: Int) {
        viewModelScope.launch {
            val lostItem = lostItemDao.getLostItemById(itemId)
            if (lostItem != null) {
                lostItemDao.deleteLostItem(lostItem)
            }
        }
    }


    fun getItemTypes(): Flow<List<ItemType>> {
        return itemTypeDao.getAllItemTypes()
    }

    fun getItemTypeById(itemTypeId: Int): ItemType? {
        return runBlocking {
            itemTypeDao.getItemTypeById(itemTypeId)
        }
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

    fun getLostItemsByPlace(placeId: Int): List<Quadruple<String, String, String?, String>> {
        return runBlocking {
            lostItemDao.getLostItemsByPlaceId(placeId).map { lostItem ->
                val itemType = itemTypeDao.getItemTypeById(lostItem.itemTypeId)
                Quadruple(
                    lostItem.itemName,
                    itemType?.name ?: "Sin tipo",
                    lostItem.description,
                    lostItem.contact
                )
            }
        }
    }

    fun getLostItemsCountByPlace(placeId: Int): Int {
        return runBlocking {
            lostItemDao.getLostItemsByPlaceId(placeId).size
        }
    }
}