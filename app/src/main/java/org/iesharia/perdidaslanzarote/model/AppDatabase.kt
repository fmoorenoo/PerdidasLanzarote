package org.iesharia.perdidaslanzarote.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.iesharia.perdidaslanzarote.model.entities.*
import org.iesharia.perdidaslanzarote.model.dao.*

@Database(entities = [LostItem::class, ItemType::class, Place::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lostItemDao(): LostItemDao
    abstract fun itemTypeDao(): ItemTypeDao
    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}