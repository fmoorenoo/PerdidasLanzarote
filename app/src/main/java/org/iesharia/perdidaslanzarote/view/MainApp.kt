package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.perdidaslanzarote.view.lostitems_screen.LostItemsScreen
import org.iesharia.perdidaslanzarote.viewmodel.AppViewModel

@Composable
fun MainApp(appViewModel: AppViewModel) {
    val currentScreen = remember { mutableStateOf("Inicio") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF7278B0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NavButton(
                text = "Inicio",
                icon = Icons.Default.Home,
                isSelected = currentScreen.value == "Inicio",
                onClick = { currentScreen.value = "Inicio" },
            )
            NavButton(
                text = "Pérdidas",
                icon = Icons.Default.Menu,
                isSelected = currentScreen.value == "Ver pérdidas",
                onClick = { currentScreen.value = "Ver pérdidas" },
            )
            NavButton(
                text = "Mapa",
                icon = Icons.Default.LocationOn,
                isSelected = currentScreen.value == "Mapa",
                onClick = { currentScreen.value = "Mapa" },
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
                "Ver pérdidas" -> LostItemsScreen(appViewModel)
                "Mapa" -> MapScreen(appViewModel)
            }
        }
    }
}

@Composable
fun NavButton(text: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF5AA1D8) else Color.Gray,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = text, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 16.sp)
        }
    }
}