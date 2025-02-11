package es.clean.architecture.ui.views.characters.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.clean.architecture.R
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.models.character.createCharacterResult
import es.clean.architecture.ui.common.Dimens.Custom110
import es.clean.architecture.ui.common.Dimens.Custom135
import es.clean.architecture.ui.common.Dimens.Custom175
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.ExtraSmall
import es.clean.architecture.ui.common.Dimens.Huge
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.Dimens.Medium
import es.clean.architecture.ui.common.Dimens.Small
import es.clean.architecture.ui.common.Dimens.Tiny
import es.clean.architecture.ui.common.LottieErrorState
import es.clean.architecture.ui.common.LottieProgressBar
import es.clean.architecture.ui.common.Numbers.TWO
import es.clean.architecture.ui.common.TextSizes.Sp12
import es.clean.architecture.ui.common.TextSizes.Sp14
import es.clean.architecture.ui.common.TextSizes.Sp20
import es.clean.architecture.ui.common.constants.CHARACTER_OBJECT
import es.clean.architecture.ui.common.lottieEmptyState
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.theme.CardBackground
import es.clean.architecture.ui.views.characters.common.CutCornersCustom
import es.clean.architecture.ui.views.characters.common.getStatusIconWithTint
import es.clean.architecture.ui.views.characters.viewmodel.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    navController: NavHostController,
    searchQuery: String?,
    charactersViewModel: CharactersViewModel = hiltViewModel(),
) {
    val characters: LazyPagingItems<RickyMortyCharacterModel.RickyMortyCharacter> =
        charactersViewModel.allCharacters.collectAsLazyPagingItems()
    LaunchedEffect(searchQuery != "") {
        if (searchQuery != null) {
            charactersViewModel.searchCharacters(searchQuery)

        }
    }

    when (characters.loadState.refresh) {
        is LoadState.Loading -> {
            LottieProgressBar()
        }

        is LoadState.NotLoading -> {

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppBackground),
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        title = {
                            Text(
                                text = stringResource(id = R.string.app_name),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = AppBackground
                        ),
                    )
                }
            ) { paddingValues ->
                if (characters.itemCount == 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(AppBackground), contentAlignment = Alignment.Center
                    ) {
                        lottieEmptyState()
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(AppBackground)
                            .padding(bottom = Large)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            items(
                                count = characters.itemCount,
                                key = characters.itemKey { character -> character.id }
                            ) { characterIndex ->
                                characters[characterIndex]?.let { item ->
                                    CharacterItem(
                                        character = item,
                                        { currentCharacter ->

                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                CHARACTER_OBJECT,
                                                value = currentCharacter
                                            )
                                            navController.navigate(Routes.CharacterDetailScreen.route)
                                        },
                                        modifier = Modifier.testTag("CharacterItem ${item.id}")
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }


        is LoadState.Error -> {
            LottieErrorState()
        }
    }
}

@Composable
fun CharacterItem(
    character: RickyMortyCharacterModel.RickyMortyCharacter,
    onItemClick: (RickyMortyCharacterModel.RickyMortyCharacter) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = CutCornersCustom(ExtraLarge),
        color = CardBackground,
        modifier = Modifier
            .testTag("CharacterItem") // Test
            .clickable { onItemClick(character) }
            .height(Custom175)
            .padding(Large),
        shadowElevation = Large
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Tiny)
                .border(
                    width = Tiny,
                    color = AppBackground,
                    shape = CutCornersCustom(ExtraLarge)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = ExtraLarge, top = Medium, start = Medium, end = Medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (statusIcon, statusTint) = getStatusIconWithTint(character.status)
                        Icon(
                            painter = painterResource(id = statusIcon),
                            contentDescription = "Status Icon",
                            tint = statusTint,
                            modifier = Modifier
                                .size(Huge)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = character.status,
                            color = Color.White,
                            fontSize = Sp14
                        )
                    }

                    Spacer(modifier = Modifier.height(ExtraSmall))

                    Text(
                        text = character.name,
                        color = Color.White,
                        fontSize = Sp20,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(Tiny))

                    Text(
                        text = "Gender: ${character.gender}",
                        color = Color.White,
                        fontSize = Sp14
                    )

                    Spacer(modifier = Modifier.height(Tiny))

                    Text(
                        text = "Location: ${character.characterLocation.name}",
                        color = Color.White,
                        fontSize = Sp12,
                        maxLines = TWO
                    )

                    Spacer(modifier = Modifier.height(ExtraSmall))
                }
                Spacer(modifier = Modifier.width(Small))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = Custom135, height = Custom110)
                        .clip(CutCornersCustom(ExtraLarge))
                        .background(CardBackground)
                )
            }
        }
    }
}


@Preview
@Composable
fun characterListScreenPreview() {
    val character = createCharacterResult()
    val onItemClick: (RickyMortyCharacterModel.RickyMortyCharacter) -> Unit = { }
    CharacterItem(character = character, onItemClick = onItemClick)
}
