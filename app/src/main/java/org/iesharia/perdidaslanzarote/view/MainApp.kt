package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.perdidaslanzarote.view.home_screen.HomeScreen
import org.iesharia.perdidaslanzarote.view.lostitems_screen.LostItemsScreen
import org.iesharia.perdidaslanzarote.view.map_screen.MapScreen
import org.iesharia.perdidaslanzarote.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(appViewModel: AppViewModel) {
    val currentScreen = remember { mutableStateOf("Inicio") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pérdidas Lanzarote", color = Color.White, fontSize = 19.sp, textAlign = TextAlign.Center) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3F71B6),
                    titleContentColor = Color.White
                ),
                actions = {
                    NavButton(
                        text = "Inicio",
                        icon = Icons.Default.Home,
                        isSelected = currentScreen.value == "Inicio",
                        onClick = { currentScreen.value = "Inicio" }
                    )
                    NavButton(
                        text = "Pérdidas",
                        icon = Icons.Default.Menu,
                        isSelected = currentScreen.value == "Ver pérdidas",
                        onClick = { currentScreen.value = "Ver pérdidas" }
                    )
                    NavButton(
                        text = "Mapa",
                        icon = Icons.Default.LocationOn,
                        isSelected = currentScreen.value == "Mapa",
                        onClick = { currentScreen.value = "Mapa" }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
        modifier = Modifier.padding(0.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(30.dp),
                tint = if (isSelected) Color(0xFFFFCA8D) else Color.White
            )
            Text(
                text = text,
                color = if (isSelected) Color(0xFFFFCA8D) else Color.White,
                fontSize = 16.sp
            )
        }
    }
}