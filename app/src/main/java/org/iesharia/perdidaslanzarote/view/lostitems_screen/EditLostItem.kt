package org.iesharia.perdidaslanzarote.view.lostitems_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import org.iesharia.perdidaslanzarote.model.entities.LostItem

@Composable
fun EditLostItem(
    lostItem: LostItem,
    onDismiss: () -> Unit,
    onSave: (LostItem) -> Unit
) {
    var itemName by remember { mutableStateOf(lostItem.itemName) }
    var description by remember { mutableStateOf(lostItem.description ?: "") }
    var contact by remember { mutableStateOf(lostItem.contact) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            IconButton(onClick = {
                onSave(lostItem.copy(itemName = itemName, description = description, contact = contact))
            }) {
                Icon(Icons.Default.Check, contentDescription = "Guardar")
            }
        },
        dismissButton = {
            IconButton(onClick = onDismiss) {
                Icon(Icons.Default.Clear, contentDescription = "Cancelar")
            }
        },
        title = { Text(text = "Editar pérdida") },
    )
}



