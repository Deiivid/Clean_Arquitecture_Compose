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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.clean.architecture.R
import es.clean.architecture.ui.views.characters.screens.detail.CutCornersShapeCustom

@Composable
fun CharacterSearchScreen() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)) {
        Image(
            painter = painterResource(id = R.drawable.searchimage),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize()
                .clip(CutCornerShape(20.dp)),
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

                SearchNameField(value = "Nombre",
                    onValueChange = { /* actualiza tu estado aquí */ },
                    placeholder = { Text("Filtrar por Nombre") })

                Spacer(modifier = Modifier.height(32.dp))

                SearchSpeciesField(
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
fun SearchNameField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit
) {
    val backgroundColor =
        MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Surface(
                shape = CutCornersShapeCustom(16.dp),
                color = backgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = LocalContentColor.current,
                        shape = CutCornersShapeCustom(16.dp)
                    )
                    .padding(10.dp),
                shadowElevation = 10.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (value.isEmpty()) {
                        placeholder()
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun SearchSpeciesField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit
) {
    val backgroundColor =
        MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Surface(
                shape = CutCornersShapeCustom(16.dp),
                color = backgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = LocalContentColor.current,
                        shape = CutCornersShapeCustom(16.dp)
                    )
                    .padding(10.dp),
                shadowElevation = 10.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (value.isEmpty()) {
                        placeholder()
                    }
                    innerTextField()
                }
            }
        }
    )
}


@Composable
fun GenderIconRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        GenderIconButton(icon = Icons.Default.Female, onClick = { /* tu función de filtro aquí */ })
        GenderIconButton(icon = Icons.Default.Male, onClick = { /* tu función de filtro aquí */ })
        GenderIconButton(
            icon = Icons.Default.Transgender,
            onClick = { /* tu función de filtro aquí */ })
    }
}

@Composable
fun StatusIconRow(
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        StatusIconButton(
            icon = painterResource(id = R.drawable.skull),
            onClick = { /* tu función de filtro aquí */ })
        StatusIconButton(
            icon = painterResource(id = R.drawable.heartbeat),
            onClick = { /* tu función de filtro aquí */ })
        StatusIconButton(
            icon = painterResource(id = R.drawable.target),
            onClick = { /* tu función de filtro aquí */ })

    }
}

@Composable
fun StatusIconButton(
    icon: Painter,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val backgroundColor =
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    val contentColor =
        MaterialTheme.colorScheme.primary

    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor)
                .clickable(
                    onClick = onClick,
                    indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(10.dp)
        )
    }
}


@Composable
fun GenderIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val backgroundColor =
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    val contentColor =
        MaterialTheme.colorScheme.onPrimary

    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor)
                .clickable(
                    onClick = onClick,
                    indication = rememberRipple(bounded = true),
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
