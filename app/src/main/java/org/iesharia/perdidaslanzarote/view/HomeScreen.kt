package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.perdidaslanzarote.model.entities.ItemType
import org.iesharia.perdidaslanzarote.model.entities.Place
import org.iesharia.perdidaslanzarote.viewmodel.AppViewModel

@Composable
fun HomeScreen(appViewModel: AppViewModel) {
    var itemName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf<ItemType?>(null) }
    var selectedPlace by remember { mutableStateOf<Place?>(null) }
    var expandedPlace by remember { mutableStateOf(false) }

    // Observar los datos desde el ViewModel
    val itemTypes by appViewModel.getItemTypes().collectAsState(initial = emptyList())
    val places by appViewModel.getPlaces().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8985B6), shape = RoundedCornerShape(15.dp))
            .padding(20.dp)
            .padding(vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFA7B1FF)),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registrar una pérdida",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
        Text(
            text = "¿Qué has perdido?",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemTypes.forEach { type ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(if (type == selectedType) Color(0xFFA7B1FF) else Color.LightGray)
                        .clickable { selectedType = type },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (type == selectedType) Color(0xFFA7B1FF) else Color.LightGray
                    )
                ) {
                    Text(
                        text = type.name,
                        modifier = Modifier.padding(16.dp),
                        color = if (type == selectedType) Color.White else Color.Black
                    )
                }
            }
        }

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text(text = "Nombre del ${(selectedType?.name)?.lowercase() ?: "Nombre"}") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción (opcional)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = false,
        )

        Text(
            text = "¿Dónde lo perdiste?",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expandedPlace = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(selectedPlace?.name ?: "Selecciona un lugar")
            }
            DropdownMenu(
                expanded = expandedPlace,
                onDismissRequest = { expandedPlace = false },
                modifier = Modifier.fillMaxWidth(),
            ) {
                places.forEach { place ->
                    DropdownMenuItem(
                        text = { Text(place.name) },
                        onClick = {
                            selectedPlace = place
                            expandedPlace = false
                        }
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD9E5FF)),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                if (itemName.isNotBlank() && selectedType != null && selectedPlace != null) {
                    appViewModel.addLostItem(
                        itemName = itemName,
                        itemTypeId = selectedType!!.id,
                        description = description,
                        placeId = selectedPlace!!.id
                    )
                    itemName = ""
                    description = ""
                    selectedType = null
                    selectedPlace = null
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Publicar pérdida",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}
