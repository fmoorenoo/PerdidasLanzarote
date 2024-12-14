package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    var itemName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf<Int?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var selectedPlace by remember { mutableStateOf<String?>(null) }

    val itemTypes = listOf("Ejemplo", "Ejemplo")
    val places = listOf("Ejemplo", "Ejemplo", "Ejemplo")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8985B6), shape = RoundedCornerShape(15.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            itemTypes.forEachIndexed { index, type ->
                Button(
                    onClick = { selectedType = index },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedType == index) Color(0xFFA7B1FF) else Color.LightGray
                    )
                ) {
                    Text(type, color = if (selectedType == index) Color.White else Color.Black)
                }
            }
        }

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Nombre del ítem") },
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
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(selectedPlace ?: "Selecciona un lugar")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                places.forEach { place ->
                    DropdownMenuItem(
                        text = { Text(place) },
                        onClick = {
                            selectedPlace = place
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
