package es.clean.architecture.ui.views.characters.screens.search

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import es.clean.architecture.R
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.ui.views.characters.common.CutCornersCustom
import es.clean.architecture.ui.views.characters.viewmodel.CharactersViewModel

@SuppressLint("StateFlowValueCalledInComposition", "ResourceAsColor")
@Composable
fun CharacterSearchScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    onSearchComplete: (String) -> Unit
) {
    val searchQuery by charactersViewModel.searchQuery.collectAsState()
    val characters: LazyPagingItems<RickyMortyCharacterModel.RickyMortyCharacter> =
        charactersViewModel.allCharacters.collectAsLazyPagingItems()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize()
                .clip(CutCornerShape(20.dp))
                .alpha(0.5f),
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
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                SearchNameField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        charactersViewModel.searchCharacters(newValue)
                    },
                    onSearch = {
                        characters.refresh()
                        charactersViewModel.searchCharacters(searchQuery)
                        onSearchComplete(searchQuery)
                    },
                    placeholder = { Text("Filtrar por Nombre") }
                )

                Spacer(modifier = Modifier.height(32.dp))


                Row(modifier = Modifier.fillMaxWidth()) {
                    GenderIconRow()

                }

                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    StatusIconRow()

                }
            }


        }
    }
}


@Composable
fun SearchNameField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    placeholder: @Composable () -> Unit
) {
    val backgroundColor =
        MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (value.isNotEmpty()) {
                    onValueChange(value)
                    onSearch()
                }
            }
        ),
        decorationBox = { innerTextField ->
            Surface(
                shape = CutCornersCustom(16.dp),
                color = backgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = LocalContentColor.current,
                        shape = CutCornersCustom(16.dp)
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
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        GenderIconButton(icon = Icons.Default.Female,
            onClick = {Toast.makeText(context, "FEMALE", Toast.LENGTH_SHORT).show() })
        GenderIconButton(icon = Icons.Default.Male,
            onClick = { Toast.makeText(context, "MAN", Toast.LENGTH_SHORT).show() })
        GenderIconButton(
            icon = Icons.Default.Transgender,
            onClick = { Toast.makeText(context, "UNKNOWN", Toast.LENGTH_SHORT).show()})
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun GenderIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val iconSize = 50.dp
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(R.color.image_background))
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
fun StatusIconRow(
) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        StatusIconButton(
            icon = painterResource(id = R.drawable.skull),
            onClick = { Toast.makeText(context, "DEAD", Toast.LENGTH_SHORT).show() })
        StatusIconButton(
            icon = painterResource(id = R.drawable.heartbeat),
            onClick = { Toast.makeText(context, "ALIVE", Toast.LENGTH_SHORT).show() })
        StatusIconButton(
            icon = painterResource(id = R.drawable.target),
            onClick = { Toast.makeText(context, "UNKNOWN", Toast.LENGTH_SHORT).show() })

    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun StatusIconButton(
    icon: Painter,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val iconSize = 50.dp
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(R.color.image_background))
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
// CharacterSearchScreen(rememberNavController(), onDialogClose = () -> Unit)
}
