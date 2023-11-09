package es.clean.architecture.ui.views.characters.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeviceUnknown
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.clean.architecture.R
import es.clean.architecture.ui.views.characters.screens.detail.CutCornersShapeCustom

@Composable
fun CharacterSearchScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.searchimage),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.app_background))
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            SearchIcon()

            Spacer(modifier = Modifier.height(16.dp))

            SearchField(
                value = "Especie",
                onValueChange = { /* actualiza tu estado aquí */ },
                placeholder = { Text("Filtrar por especie") }
            )

            Spacer(modifier = Modifier.height(32.dp))

            GenderIconRow()

            Spacer(modifier = Modifier.height(32.dp))

            StatusIconRow()

        }
    }
}

@Composable
fun StyledTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
            .clip(CutCornersShapeCustom(16.dp)) // Usando la forma personalizada
            .border(2.dp, colorResource(id = R.color.app_background), CutCornersShapeCustom(16.dp))
            .background(colorResource(id = R.color.card_background))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) { // Muestra el placeholder si el campo está vacío
                Text(placeholder, color = Color.Gray)
            }
            innerTextField()
        }
    )
}

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Surface(
                shape = CutCornersShapeCustom(16.dp),
                color = colorResource(id = R.color.card_background),
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.app_background),
                        shape = CutCornersShapeCustom(16.dp)
                    )
                    .padding(10.dp),
                shadowElevation = 10.dp
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    innerTextField() // This is where the actual TextField is placed
                    placeholder()
                }
            }
        }
    )
}

@Composable
fun GenderIconRow() {
    // Organiza los iconos de género en una fila centrada
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly, // Espaciado uniforme entre los iconos
        verticalAlignment = Alignment.CenterVertically, // Centrar verticalmente en la fila
        modifier = Modifier.fillMaxWidth() // Llenar el ancho máximo disponible
    ) {
        GenderIconButton(icon = Icons.Default.Female, onClick = { /* tu función de filtro aquí */ })
        GenderIconButton(icon = Icons.Default.Male, onClick = { /* tu función de filtro aquí */ })
        GenderIconButton(
            icon = Icons.Default.Transgender,
            onClick = { /* tu función de filtro aquí */ })
        // Añade más botones si es necesario
    }
}

@Composable
fun StatusIconRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly, // Espaciado uniforme entre los iconos
        verticalAlignment = Alignment.CenterVertically, // Alineación vertical centrada
        modifier = Modifier.fillMaxWidth() // Llenar el ancho disponible
    ) {
        // Botón para el estado "Alive"
        IconButton(onClick = { /* tu función de filtro aquí */ }) {
            Icon(
                painter = painterResource(id = R.drawable.heartbeat), // Asume que tienes este drawable
                contentDescription = "Alive",
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.card_background),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(10.dp)
            )
        }

        // Botón para el estado "Dead"
        IconButton(onClick = { /* tu función de filtro aquí */ }) {
            Icon(
                painter = painterResource(id = R.drawable.skull), // Asume que tienes este drawable
                contentDescription = "Dead",
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.card_background),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(10.dp)
            )
        }

        // Botón para el estado "Unknown"
        IconButton(onClick = { /* tu función de filtro aquí */ }) {
            Icon(
                painter = painterResource(id = R.drawable.target), // Asume que tienes este drawable
                contentDescription = "Unknown",
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.card_background),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(10.dp)
            )
        }
    }
}
@Composable
fun SearchIcon() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.searchimage),
            contentDescription = "Busqueda",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun GenderIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null, // Asegúrate de definir una descripción adecuada para la accesibilidad.
            tint = tint,
            modifier = Modifier
                .clip(CutCornersShapeCustom(16.dp))
                .background(colorResource(id = R.color.card_background))
                .padding(10.dp)
        )
    }
}

@Preview
@Composable
fun ShowCharacterSearchScreen() {
    CharacterSearchScreen()
}
