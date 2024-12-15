package org.iesharia.perdidaslanzarote.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

                Marker(
                    state = markerState,
                    title = place.name,
                )
            }
        }
    }
}