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
    var selectedType by remember { mutableStateOf<Int?>(null) }
    val itemTypes = listOf("Ejemplo", "Ejemplo")

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
                    text = "¿Qué has perdido?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
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
    }
}