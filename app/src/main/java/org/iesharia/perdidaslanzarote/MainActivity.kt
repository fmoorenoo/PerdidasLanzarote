package org.iesharia.perdidaslanzarote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.iesharia.perdidaslanzarote.model.AppDatabase
import org.iesharia.perdidaslanzarote.view.MainApp
import org.iesharia.perdidaslanzarote.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar la base de datos con los DAO y el ViewModel
        val database = AppDatabase.getDatabase(this)
        val lostItemDao = database.lostItemDao()
        val itemTypeDao = database.itemTypeDao()
        val placeDao = database.placeDao()

        val mapViewModel = AppViewModel(lostItemDao, itemTypeDao, placeDao)
        setContent {
            MainApp(mapViewModel)
        }
    }
}
