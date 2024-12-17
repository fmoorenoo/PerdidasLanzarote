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
   - Los usuarios ingresan el nombre, tipo, descripción (opcional), contacto y localización del objeto perdido en la sección inicial 'Inicio'.
   - La información se almacena de forma persistente en Room.

2. **Visualización en el Mapa:**
   - Los objetos perdidos se muestran en la sección de 'Pérdidas', y en la sección 'Mapa', mediante un marcador que indica el número de pérdidas de ese lugar.

3. **Interacción con el Mapa:**
   - Al hacer clic en un marcador, se despliega una ventana con los detalles de cada pérdida en ese lugar.
  
## 📷 Resultado final  
| Inicio | Ver pérdidas | Mapa |
| -------------- | --------------- | --------------- |
| <img width="350" src="https://github.com/user-attachments/assets/ac74cd3c-69db-4361-b9fd-8a16d3e8ec1d"> | <img width="350" src="https://github.com/user-attachments/assets/ffb0cb2a-a6cd-4680-8e49-cd389313e26a"> | <img width="350" src="https://github.com/user-attachments/assets/cac6e7fb-c331-4101-bf7c-44f268321159"> |

<br>

## ⚙️ Descarga el proyecto
Puedes clonar el repositorio mediante el siguiente enlace, o descargar el archivo en un .zip
https://github.com/fmoorenoo/perdidasLanzarote.git

En Android Studio puedes clonarlo de la siguiente manera
![image](https://github.com/user-attachments/assets/e42c3710-9584-48b3-a2e9-6ea27bf541d9)
