package es.clean.architecture.ui.views.characters.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.*
import es.clean.architecture.R
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.models.character.createCharacterResult
import es.clean.architecture.ui.common.CHARACTER_OBJECT
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.views.characters.common.getStatusIconWithTint
import es.clean.architecture.ui.views.characters.screens.detail.CutCornersShapeCustom
import es.clean.architecture.ui.views.characters.viewmodel.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel = hiltViewModel(),
) {
    val characters = charactersViewModel.allCharacters.collectAsLazyPagingItems()
    /* val scope = rememberCoroutineScope()
     val context = LocalContext.current
    */
    val isSearching by remember {
        mutableStateOf(false)
    }
    // var searchString by remember {
    //   mutableStateOf("")
    //}
    val charactersr: LazyPagingItems<RickyMortyCharacterModel.RickyMortyCharacter> =
        charactersViewModel.allCharacters.collectAsLazyPagingItems()


    when (charactersr.loadState.refresh) {
        is LoadState.Loading -> {
            // Mostrar animación de carga
            LottieProgressBar()
        }

        is LoadState.NotLoading -> {

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.app_background)),
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        title = {
                            if (!isSearching) {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            } else {
                                /* TextField(
                                      modifier = Modifier
                                          .padding(end = 12.dp)
                                          .fillMaxWidth(),
                                      value = searchString,
                                      onValueChange = { newSearchString ->
                                          searchString = newSearchString
                                      },
                                      label = { Text("Cadena de Búsqueda") },
                                      maxLines = 1
                                  )*/
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                        /* actions = {
                             IconButton(onClick = { isSearching = !isSearching }) {
                                 Icon(
                                     imageVector = Icons.Default.Search,
                                     contentDescription = null,
                                     tint = MaterialTheme.colorScheme.onPrimary
                                 )
                             }
                         }*/
                    )
                }
            ) { paddingValues ->
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
                            ) { currentCharacter ->

                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    CHARACTER_OBJECT,
                                    value = currentCharacter
                                )
                                navController.navigate(Routes.CharacterDetailScreen.route)
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
    onItemClick: (RickyMortyCharacterModel.RickyMortyCharacter) -> Unit
) {
    Surface(
        shape = CutCornersShapeCustom(16.dp),
        color = colorResource(id = R.color.card_background),
        modifier = Modifier
            .clickable { onItemClick(character) }
            .height(165.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = CutCornersShapeCustom(16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, top = 8.dp, start = 8.dp, end = 8.dp),
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
                                .size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = character.status,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = character.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Gender: ${character.gender}",
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Location: ${character.characterLocation.name}",
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 135.dp, height = 110.dp)
                        .clip(CutCornersShapeCustom(16.dp))
                        .background(colorResource(id = R.color.card_border))
                )
            }
        }
    }
}


@Composable
fun LottieProgressBar() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loadinglottie))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun LottieErrorState() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.cryricky))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.fillMaxSize()
    )

}

@Preview
@Composable
fun CharacterListScreenPreview() {
    val character = createCharacterResult()
    val onItemClick: (RickyMortyCharacterModel.RickyMortyCharacter) -> Unit = { }
    CharacterItem(character = character, onItemClick = onItemClick)
}
