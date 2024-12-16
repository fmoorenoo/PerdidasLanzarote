# 📲 Pérdidas Lanzarote

## 📝 Descripción

**Pérdidas Lanzarote** es una aplicación que permite publicar objetos perdidos en la isla de Lanzarote. Los usuarios pueden registrar información sobre lo que han perdido, incluyendo su nombre, tipo, una descripción opcional, datos de contacto y el lugar donde se perdió.

<br>

## 📂 Detalles

- **Base de Datos:** Implementada con Room, con tres tablas:

<br>

| lost_item |                  
|---|
| id (PK) |
| itemName |
| itemTypeId (FK) |
| contact |
| description |
| placeId (FK) |

<br>

| item_type |
|---|
| id (PK) |
| name |

<br>

| place |
|---|
| id (PK) |
| name |
| longitude |
| latitude |

<br>

- **Datos:** Los usuarios pueden ver, agregar, modificar y eliminar pérdidas.
- **Gestión de Datos:** Uso de `ViewModel` para conectar Room con los componentes de la interfaz, quitándo 'trabajo' a la vista.
- **Mapa:** Mapa integrado para ver con mayor claridad la ubicación de las pérdidas publicadas.

<br>

## ⚙️ Funcionamiento

1. **Registro de Objetos Perdidos:**
   - Los usuarios ingresan detalles como el nombre, tipo, descripción (opcional), contacto y localización del objeto perdido en la sección inicial 'Inicio'.
   - La información se almacena de forma persistente en Room.

2. **Visualización en el Mapa:**
   - Los objetos perdidos se muestran en la sección de 'Pérdidas', y en la sección 'Mapa', mediante un marcador que indica el número de pérdidas de ese lugar.

3. **Interacción con el Mapa:**
   - Al hacer clic en un marcador, se despliega una ventana con los detalles de cada pérdida en ese lugar.

