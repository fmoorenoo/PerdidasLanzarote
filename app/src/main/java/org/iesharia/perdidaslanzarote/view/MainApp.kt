package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainApp() {
    val currentScreen = remember { mutableStateOf("Inicio") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF505050)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { currentScreen.value = "Inicio" },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Inicio", color = Color.White)
            }
            Button(
                onClick = { currentScreen.value = "Ver pérdidas" },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Ver pérdidas", color = Color.White)
            }
            Button(
                onClick = { currentScreen.value = "Mapa" },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Mapa", color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (currentScreen.value) {
                "Inicio" -> " "// LLamar a la pantalla de inicio
                "Ver pérdidas" -> "" // Llamar a la pantalla de ver pérdidas
                "Mapa" -> "" // Llamar a la pantalla de mapa
            }
        }
    }
}