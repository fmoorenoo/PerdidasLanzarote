package org.iesharia.perdidaslanzarote.view.lostitems_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (lostItems.isEmpty()) {
            Text(text = "No hay pérdidas registradas")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(lostItems) { lostItem ->
                val lostItemType = appViewModel.getItemTypeById(lostItem.itemTypeId)
                val place = appViewModel.getPlaceById(lostItem.placeId)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E5FF)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = lostItem.itemName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                        Text(text = "Tipo: ${lostItemType?.name ?: "Sin tipo"}", fontSize = 16.sp)
                        Text(text = "Lugar: ${place.name}", fontSize = 16.sp)
                        Text(
                            text = "Descripción: ${lostItem.description ?: "Sin descripción"}",
                            fontSize = 16.sp,
                        )
                        Text(
                            text = "Contacto: ${lostItem.contact}",
                            fontSize = 16.sp,
                            color = Color.Blue
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = {
                                    itemToEdit = lostItem
                                    showEditor = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xCB6574FA)),
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier.width(160.dp)
                            ) {
                                Text(text = "Editar", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(20.dp))
                                Icon(
                                    imageVector = Icons.Default.Create,
                                    contentDescription = "Edit",
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                            Button(
                                onClick = { appViewModel.deleteLostItem(lostItem.id) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xCBFF6767)),
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier.width(160.dp)
                            ) {
                                Text(text = "Eliminar", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(20.dp))
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    modifier = Modifier.size(25.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
        if (showEditor && itemToEdit != null) {
            EditLostItem(
                lostItem = itemToEdit!!,
                onDismiss = { showEditor = false },
                onSave = { updatedItem ->
                    if (updatedItem.itemName.isNotBlank() && updatedItem.contact.isNotBlank() && updatedItem.contact.length == 9) {
                        appViewModel.updateLostItem(updatedItem)
                        showEditor = false
                    } else if (updatedItem.contact.length != 9) {
                        Toast.makeText(context, "El número de teléfono debe tener 9 dígitos", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Completa los campos obligatorios", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}