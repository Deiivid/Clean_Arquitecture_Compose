package es.clean.architecture.ui.views.episodes.list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import es.clean.architecture.R
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.domain.episodes.models.createEpisodesResult
import es.clean.architecture.ui.common.Dimensions
import es.clean.architecture.ui.views.common.LottieErrorState
import es.clean.architecture.ui.views.common.LottieProgressBar
import es.clean.architecture.ui.views.episodes.viewmodel.EpisodesViewModel
import java.text.SimpleDateFormat
import java.util.Locale

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
                    .background(colorResource(id = R.color.app_background)),

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
                            colorResource(id = R.color.app_background)
                        )
                        .padding(bottom = 10.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
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
                                    {
                                        Toast.makeText(
                                            context,
                                            "Has Pulsado en un elemento ${item.name}",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    },
                                    modifier = Modifier.testTag("EpisodeItem ${item.id}")
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
    modifier: Modifier = Modifier
) {
    val borderWidth = 2.dp
    val borderColor = Color.White

    val scale = remember { Animatable(1f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    val itemHeight = 160.dp
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Surface(
            shape = RoundedCornerShape(Dimensions.medium),
            color = Color(0xFFDAE1E7),
            modifier = Modifier
                .clickable { onItemClick(episodes) }
                .height(itemHeight)
                .padding(8.dp)
                .border(borderWidth, borderColor, shape = RoundedCornerShape(Dimensions.medium)),
            shadowElevation = Dimensions.thin
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
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
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(Dimensions.small))
                    Text(
                        text = formatEmissionDate(episodes.airDate),
                        fontSize = 14.sp,
                        maxLines = 1,
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
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = episodes.url,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Surface(
            shape = CircleShape,
            color = colorResource(id = R.color.app_background),
            modifier = Modifier
                .size(32.dp)
                .offset(y = (-16).dp)
                .align(Alignment.TopCenter)
                .border(borderWidth, borderColor, shape = RoundedCornerShape(12.dp))

        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = episodes.id.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 16.sp,
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
