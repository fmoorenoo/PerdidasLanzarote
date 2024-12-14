package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
                .padding(top = 15.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            NavButton(
                text = "Inicio",
                isSelected = currentScreen.value == "Inicio",
                onClick = { currentScreen.value = "Inicio" }
            )
            NavButton(
                text = "Ver pérdidas",
                isSelected = currentScreen.value == "Ver pérdidas",
                onClick = { currentScreen.value = "Ver pérdidas" }
            )
            NavButton(
                text = "Mapa",
                isSelected = currentScreen.value == "Mapa",
                onClick = { currentScreen.value = "Mapa" }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (currentScreen.value) {
                "Inicio" -> ""// Llamar a la pantalla de inicio
                "Ver pérdidas" -> "" // Llamar a la pantalla de ver pérdidas
                "Mapa" -> "" // Llamar a la pantalla de mapa
            }
        }
    }
}

@Composable
fun NavButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RectangleShape, // Forma rectangular (sin curvas)
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF757575) else MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(text = text)
    }
}
