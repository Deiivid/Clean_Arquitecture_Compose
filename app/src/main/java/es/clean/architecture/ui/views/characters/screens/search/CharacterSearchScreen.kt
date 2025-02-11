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

                Spacer(modifier = Modifier.height(Massive))

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


@Preview
@Composable
fun ShowCharacterSearchScreen() {
// CharacterSearchScreen(rememberNavController(), onDialogClose = () -> Unit)
}
