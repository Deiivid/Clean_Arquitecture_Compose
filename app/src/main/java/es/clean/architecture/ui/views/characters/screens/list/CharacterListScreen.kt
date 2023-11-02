package es.clean.architecture.ui.views.characters.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.*
import es.clean.architecture.R
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.models.character.createCharacterResult
import es.clean.architecture.ui.common.navigation.routes.Routes
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
            // Si no está cargando, muestras los personajes en el LazyColumn
            Scaffold(
                modifier = Modifier.fillMaxSize(),
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
                                    "character",
                                    value = currentCharacter
                                )
                                navController.navigate(Routes.CharacterDetailScreen.route)

                                /* scope.launch {
                                     withContext(Dispatchers.Main){
                                         Toast.makeText(context, "Personaje: ${currentCharacter.name}", Toast.LENGTH_LONG).show()
                                     }
                                 }
                             }*/
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
    onItemClick: (rickyMortyCharacter: RickyMortyCharacterModel.RickyMortyCharacter) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .clickable {
                onItemClick(character)
            }
            .height(165.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(bottom = 14.dp, top = 8.dp, start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = character.status,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 2.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFD1D5E1))
                            .padding(2.dp) // Este padding es para el espacio dentro del fondo, alrededor del texto.
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = character.name,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = character.gender)

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = character.characterLocation.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(width = 135.dp, height = 110.dp)
                    .padding(top = 10.dp),
                contentAlignment = Alignment.Center,
                content = {
                    Image(
                        painter = rememberAsyncImagePainter(model = character.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    )
                }
            )
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
