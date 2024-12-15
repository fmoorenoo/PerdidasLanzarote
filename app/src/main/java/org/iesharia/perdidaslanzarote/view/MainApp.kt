package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.perdidaslanzarote.viewmodel.AppViewModel

@Composable
fun MainApp(appViewModel: AppViewModel) {
    val currentScreen = remember { mutableStateOf("Inicio") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF8A8A8A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
                .background(Color(0xFFD7A3FF)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NavButton(
                text = "Inicio",
                isSelected = currentScreen.value == "Inicio",
                onClick = { currentScreen.value = "Inicio" },
                modifier = Modifier.weight(1f)
            )
            NavButton(
                text = "Ver pérdidas",
                isSelected = currentScreen.value == "Ver pérdidas",
                onClick = { currentScreen.value = "Ver pérdidas" },
                modifier = Modifier.weight(1f)
            )
            NavButton(
                text = "Mapa",
                isSelected = currentScreen.value == "Mapa",
                onClick = { currentScreen.value = "Mapa" },
                modifier = Modifier.weight(1f)
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
                "Inicio" -> HomeScreen(appViewModel)
                "Ver pérdidas" -> "" // Llamar a la pantalla de ver pérdidas
                "Mapa" -> MapScreen(appViewModel)
            }
        }
    }
}

@Composable
fun NavButton(text: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF757575) else MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(horizontal = 2.dp),
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}
