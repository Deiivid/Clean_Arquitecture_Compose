package es.clean.architecture.ui.views.episodes.list

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
import com.airbnb.lottie.compose.*
import es.clean.architecture.R
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.views.episodes.viewmodel.EpisodesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesListScreen(
    navController: NavHostController,
    episodesViewModel: EpisodesViewModel = hiltViewModel(),
) {
    val characters = episodesViewModel.allEpisodes.collectAsLazyPagingItems()
    /* val scope = rememberCoroutineScope()
     val context = LocalContext.current
    */
    val isSearching by remember {
        mutableStateOf(false)
    }
    // var searchString by remember {
    //   mutableStateOf("")
    //}
    val episodes: LazyPagingItems<RickyMortyEpisodesModel.Episode> =
        episodesViewModel.allEpisodes.collectAsLazyPagingItems()


    when (episodes.loadState.refresh) {
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
                            EpisodesItem(
                                episodes = item,
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
fun EpisodesItem(
    episodes: RickyMortyEpisodesModel.Episode,
    onItemClick: (rickyMortyEpisode: RickyMortyEpisodesModel.Episode) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .clickable {
                onItemClick(episodes)
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
                        text = episodes.name,
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
                    text = episodes.name,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = episodes.airDate)

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = episodes.url,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))

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
    /*val character = createCharacterResult()
    val onItemClick: (RickyMortyCharacterModel.RickyMortyCharacter) -> Unit = { }
    EpisodesItem(episodes = , onItemClick = )
*/
}
