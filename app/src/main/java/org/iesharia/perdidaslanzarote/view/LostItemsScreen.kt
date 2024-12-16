package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.perdidaslanzarote.model.entities.LostItem
import org.iesharia.perdidaslanzarote.viewmodel.AppViewModel

@Composable
fun LostItemsScreen(appViewModel: AppViewModel) {
    val lostItems by appViewModel.getAllLostItems().collectAsState(initial = emptyList())
    var showEditor by remember { mutableStateOf(false) }
    var itemToEdit by remember { mutableStateOf<LostItem?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Todas las pérdidas",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (lostItems.isEmpty()) {
            Text(text = "No hay pérdidas registradas")
        }

        lostItems.forEach { lostItem ->
            val lostItemType = appViewModel.getItemTypeById(lostItem.itemTypeId)
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Nombre: ${lostItem.itemName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(text = "Tipo: ${lostItemType?.name}", fontSize = 18.sp)
                    Text(
                        text = "Descripción: ${lostItem.description ?: "Sin descripción"}",
                        fontSize = 18.sp
                    )
                    Text(text = "Contacto: ${lostItem.contact}", fontSize = 18.sp)
                }
            }
        }
    }
}