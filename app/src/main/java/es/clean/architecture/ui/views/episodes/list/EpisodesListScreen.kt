package es.clean.architecture.ui.views.episodes.list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Language
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import es.clean.architecture.domain.episodes.models.createEpisodesResult
import es.clean.architecture.ui.views.episodes.viewmodel.EpisodesViewModel
import java.text.SimpleDateFormat
import java.util.Locale

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

    val context = LocalContext.current
    when (episodes.loadState.refresh) {
        is LoadState.Loading -> {
            // Mostrar animación de carga
            LottieProgressBar()
        }

        is LoadState.NotLoading -> {
            // Si no está cargando, muestras los personajes en el LazyColumn
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            colorResource(id = R.color.app_background)
                        )
                        .padding(bottom = 10.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(
                            count = episodes.itemCount,
                            key = episodes.itemKey { episode -> episode.id }
                        ) { episodesIndex ->
                            episodes[episodesIndex]?.let { item ->
                                EpisodesItem(
                                    episodes = item,
                                ) { currentEpisode ->

                                    Toast.makeText(
                                        context,
                                        "Has Pulsado en un elemento",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    /* navController.currentBackStackEntry?.savedStateHandle?.set(
                                         EPISODE_OBJECT,
                                         value = currentEpisode
                                     )
                                     navController.navigate(Routes.EpisodeDetailScreen.route)
                                 */
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
fun EpisodesItem(
    episodes: RickyMortyEpisodesModel.Episode,
    onItemClick: (rickyMortyEpisode: RickyMortyEpisodesModel.Episode) -> Unit,
) {
    val context = LocalContext.current // Obtener el Context

    val scale = remember { Animatable(1f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.1f, // Escala hasta un 10% más grande
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    var pressed by remember { mutableStateOf(false) }

    val scaleRow by animateFloatAsState(
        targetValue = if (pressed) 1.1f else 1f,
        animationSpec = tween(durationMillis = 100), label = "Row"
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .clickable {
                onItemClick(episodes)
            }
            .height(185.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(bottom = 14.dp, top = 8.dp, start = 8.dp),
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(scale.value),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        ) {
                        Text(
                            text = episodes.name,
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFEEF7F3))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Icon(
                        imageVector = Icons.Default.Bookmarks,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = episodes.episode,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }


                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Emision Date:")
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = formatEmisionDate(episodes.airDate),
                        maxLines = 2,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scaleRow
                            scaleY = scaleRow
                        }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    pressed = true
                                    tryAwaitRelease()
                                    pressed = false
                                },
                                onTap = {
                                    openUrl(episodes.url, context)
                                }
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                    Text(
                        text = episodes.url,
                        fontSize = 12.sp,
                        maxLines = 2,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleMedium,

                        )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))

        }
    }
}

fun formatEmisionDate(dateString: String): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val parsedDate = parser.parse(dateString)
        formatter.format(parsedDate)
    } catch (e: Exception) {
        // Manejar excepción si el formato de fecha es incorrecto
        dateString
    }
}

fun openUrl(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
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
    val character = createEpisodesResult()
    val onItemClick: (RickyMortyEpisodesModel.Episode) -> Unit = { }
    EpisodesItem(episodes = character, onItemClick = onItemClick)

}
