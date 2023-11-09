package es.clean.architecture.ui.views.characters.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start // Alineación a la izquierda
            ) {
                Spacer(modifier = Modifier.height(32.dp))
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
fun GenderIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val backgroundColor =
        MaterialTheme.colorScheme.primary.copy(alpha = 0.4f) // Fondo ligero con transparencia
    val contentColor =
        MaterialTheme.colorScheme.onPrimary // Color del contenido que garantiza contraste

    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor)
                .clickable( // Efecto al hacer clic
                    onClick = onClick,
                    indication = rememberRipple(bounded = true), // Efecto de onda al tocar
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(10.dp)
        )
    }
}

@Preview
@Composable
fun ShowCharacterSearchScreen() {
    CharacterSearchScreen()
}
