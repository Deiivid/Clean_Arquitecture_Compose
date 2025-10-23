package es.clean.architecture.ui.views.characters.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import es.clean.architecture.R
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.ui.common.Dimens.Custom600
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.Huge
import es.clean.architecture.ui.common.Dimens.Massive
import es.clean.architecture.ui.views.characters.screens.search.customization.GenderIconRow
import es.clean.architecture.ui.views.characters.screens.search.customization.SearchNameField
import es.clean.architecture.ui.views.characters.screens.search.customization.StatusIconRow
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
            .height(Custom600)

    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize()
                .clip(CutCornerShape(Huge))
                .alpha(0.5f),
            contentScale = ContentScale.Crop
        )
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(ExtraLarge),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            shape = RoundedCornerShape(ExtraLarge)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ExtraLarge),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(Massive))

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
                    placeholder = { Text(R.string.filter.toString()) }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    GenderIconRow()
                }

                Spacer(modifier = Modifier.height(Massive))
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
                shape = CutCornersCustom(ExtraLarge),
                color = backgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = Tiny,
                        color = LocalContentColor.current,
                        shape = CutCornersCustom(ExtraLarge)
                    )
                    .padding(Large),
                shadowElevation = Large
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(ExtraLarge)
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
        GenderIconButton(
            icon = Icons.Default.Female,
            onClick = { Toast.makeText(context, "FEMALE", Toast.LENGTH_SHORT).show() }
        )
        GenderIconButton(
            icon = Icons.Default.Male,
            onClick = { Toast.makeText(context, "MAN", Toast.LENGTH_SHORT).show() }
        )
        GenderIconButton(
            icon = Icons.Default.Transgender,
            onClick = { Toast.makeText(context, "UNKNOWN", Toast.LENGTH_SHORT).show() }
        )
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun GenderIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val iconSize = Custom55
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(ExtraLarge))
                .background(ImageBackground)
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
fun StatusIconRow() {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        StatusIconButton(
            icon = painterResource(id = R.drawable.skull),
            onClick = { Toast.makeText(context, "DEAD", Toast.LENGTH_SHORT).show() }
        )
        StatusIconButton(
            icon = painterResource(id = R.drawable.heartbeat),
            onClick = { Toast.makeText(context, "ALIVE", Toast.LENGTH_SHORT).show() }
        )
        StatusIconButton(
            icon = painterResource(id = R.drawable.target),
            onClick = { Toast.makeText(context, "UNKNOWN", Toast.LENGTH_SHORT).show() }
        )
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun StatusIconButton(
    icon: Painter,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val iconSize = Custom55
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(ExtraLarge))
                .background(ImageBackground)
                .clickable(
                    onClick = onClick,
                    indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(Large)
        )
    }
}

@Preview
@Composable
fun ShowCharacterSearchScreen() {
// CharacterSearchScreen(rememberNavController(), onDialogClose = () -> Unit)
}
