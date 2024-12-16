package org.iesharia.perdidaslanzarote.view.lostitems_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Nombre del ítem") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Contacto") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }
        }
    )
}



