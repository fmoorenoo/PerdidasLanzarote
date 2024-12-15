package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import org.iesharia.perdidaslanzarote.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.utsman.osmandcompose.*
import org.iesharia.perdidaslanzarote.viewmodel.AppViewModel
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.util.MapTileIndex

val GoogleSat: OnlineTileSourceBase = object : XYTileSource(
    "Google-Sat",
    0, 19, 256, ".png", arrayOf(
        "https://mt0.google.com",
        "https://mt1.google.com",
        "https://mt2.google.com",
        "https://mt3.google.com",
    )
) {
    override fun getTileURLString(pTileIndex: Long): String {
        return baseUrl + "/vt/lyrs=s&x=" + MapTileIndex.getX(pTileIndex) + "&y=" + MapTileIndex.getY(
            pTileIndex
        ) + "&z=" + MapTileIndex.getZoom(pTileIndex)
    }
}

@Composable
fun MapScreen(appViewModel: AppViewModel) {
    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(29.03668877801322, -13.641841146662419)
        zoom = 11.9
    }

    var mapProperties by remember { mutableStateOf(DefaultMapProperties) }
    val placesWithLostItems by appViewModel.getPlacesWithLostItems().collectAsState(initial = emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        OpenStreetMap(modifier = Modifier.fillMaxSize(), cameraState = cameraState, properties = mapProperties) {
            mapProperties = mapProperties
                .copy(tileSources = GoogleSat)
                .copy(isEnableRotationGesture = true)
                .copy(zoomButtonVisibility = ZoomButtonVisibility.SHOW_AND_FADEOUT)

            placesWithLostItems.forEach { place ->
                val markerState = rememberMarkerState(
                    geoPoint = GeoPoint(
                        place.latitude.toDouble(),
                        place.longitude.toDouble()
                    )
                )

                val lostItemsCount = appViewModel.getLostItemsCountByPlace(place.id)
                val context = LocalContext.current

                // Icono según la cantidad de pérdidas en el lugar
                val markerImage = when {
                    lostItemsCount == 1 -> R.drawable.one_item
                    lostItemsCount == 2 -> R.drawable.two_items
                    lostItemsCount == 3 -> R.drawable.three_items
                    lostItemsCount > 3 -> R.drawable.more_items
                    else -> R.drawable.one_item
                }

                val drawable = ContextCompat.getDrawable(context, markerImage)
                val lostItems = appViewModel.getLostItemsByPlace(place.id)

                Marker(
                    state = markerState,
                    title = place.name,
                    icon = drawable
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                color = Color(0xD0000000),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Nombre del lugar
                        Text(
                            text = it.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        // Mostrar pérdidas
                        lostItems.forEach { (itemName, itemType, description, contact) ->
                            var showDescription by remember { mutableStateOf(false) }

                            Card(
                                modifier = Modifier
                                    .widthIn(0.dp, 250.dp)
                                    .padding(vertical = 4.dp)
                                    .background(Color(0xFF424242)),
                                shape = RectangleShape
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(4.dp)
                                ) {
                                    // Tipo de ítem y tfno de contacto
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp).padding(top = 3.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = itemType,
                                            fontWeight = FontWeight(500),
                                            fontSize = 17.sp,
                                            modifier = Modifier
                                                .clip(shape = RoundedCornerShape(6.dp))
                                                .background(Color(0xFFFC9E9E))
                                                .padding(horizontal = 8.dp, vertical = 4.dp),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "Tfno: $contact",
                                            fontSize = 17.sp,
                                        )
                                    }


                                    // Nombre y descripción
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = itemName,
                                            fontWeight = FontWeight(500),
                                            fontSize = 20.sp,
                                            modifier = Modifier.padding(vertical = 10.dp)
                                        )

                                        // Icono de 'mostrar más' si hay descripción
                                        if (!description.isNullOrEmpty()) {
                                            IconButton(
                                                onClick = { showDescription = !showDescription }
                                            ) {
                                                Icon(
                                                    imageVector = if (showDescription) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                                    contentDescription = if (showDescription) "Ocultar" else "Mostrar",
                                                    tint = Color(0xFF5A67D8),
                                                    modifier = Modifier.size(30.dp)
                                                )
                                            }
                                        }
                                    }

                                    // Descripción
                                    if (showDescription && !description.isNullOrEmpty()) {
                                        Text(
                                            text = description,
                                            color = Color.DarkGray,
                                            fontSize = 17.sp,
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}