package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.layout.*
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

        lostItems.forEach { lostItem ->
            val lostItemType = appViewModel.getItemTypeById(lostItem.itemTypeId)
        }
    }
}