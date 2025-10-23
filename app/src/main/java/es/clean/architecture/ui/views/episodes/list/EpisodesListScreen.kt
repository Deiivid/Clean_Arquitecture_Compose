package es.clean.architecture.ui.views.episodes.list

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import es.clean.architecture.R
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.domain.episodes.models.createEpisodesResult
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.LottieErrorState
import es.clean.architecture.ui.common.LottieProgressBar
import es.clean.architecture.ui.common.Numbers.TWO
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.views.episodes.list.item.EpisodesItem
import es.clean.architecture.ui.views.episodes.viewmodel.EpisodesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesListScreen(
    episodesViewModel: EpisodesViewModel = hiltViewModel(),
) {
    val episodes: LazyPagingItems<RickyMortyEpisodesModel.Episode> =
        episodesViewModel.allEpisodes.collectAsLazyPagingItems()

    val context = LocalContext.current
    when (episodes.loadState.refresh) {
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
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            AppBackground
                        )
                        .padding(bottom = Large)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(TWO),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(
                            count = episodes.itemCount,
                            key = episodes.itemKey { episode -> episode.id }
                        ) { episodesIndex ->
                            episodes[episodesIndex]?.let { item ->
                                val selectedItem =
                                    "${stringResource(R.string.selected_item)} ${item.name}"
                                EpisodesItem(
                                    episodes = item,
                                    {
                                        Toast.makeText(
                                            context,
                                            selectedItem,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                )
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

@SuppressLint("ResourceAsColor")
@Composable
fun EpisodesItem(
    episodes: RickyMortyEpisodesModel.Episode,
    onItemClick: (rickyMortyEpisode: RickyMortyEpisodesModel.Episode) -> Unit,
) {
    val borderWidth = TWO.dp
    val borderColor = Color.White

    val scale = remember { Animatable(1f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = FIVE_HUNDRED, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    val itemHeight = Custom160
    Box(
        modifier = Modifier
            .padding(top = ExtraLarge)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Surface(
            shape = RoundedCornerShape(Medium),
            color = Color(0xFFDAE1E7),
            modifier = Modifier
                .clickable { onItemClick(episodes) }
                .height(itemHeight)
                .padding(Medium)
                .border(borderWidth, borderColor, shape = RoundedCornerShape(Medium)),
            shadowElevation = Tiny
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Medium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scale.value)
                ) {
                    Text(
                        text = episodes.name,
                        fontSize = Sp16,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        maxLines = TWO,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(ExtraLarge)
                    )
                    Spacer(modifier = Modifier.width(Small))
                    Text(
                        text = formatEmissionDate(episodes.airDate),
                        fontSize = Sp14,
                        maxLines = ONE,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = Color.DarkGray,
                        modifier = Modifier.size(ExtraLarge)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = episodes.url,
                        fontSize = Sp12,
                        maxLines = ONE,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Surface(
            shape = CircleShape,
            color = AppBackground,
            modifier = Modifier
                .size(Massive)
                .offset(y = (-SIXTEEN).dp)
                .align(Alignment.TopCenter)
                .border(borderWidth, borderColor, shape = RoundedCornerShape(Large))

        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = episodes.id.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = Sp16,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

fun formatEmissionDate(dateString: String): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val parsedDate = parser.parse(dateString)
        formatter.format(parsedDate)
    } catch (ex: Exception) {
        Log.e("Episodes", "Error loading episodes", ex)
        dateString
    }
}

@Preview
@Composable
fun CharacterListScreenPreview() {
    val character = createEpisodesResult()
    val onItemClick: (RickyMortyEpisodesModel.Episode) -> Unit = { }
    EpisodesItem(episodes = character, onItemClick = onItemClick)
}
