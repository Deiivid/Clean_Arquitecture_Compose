package es.clean.architecture.ui.views.episodes.list

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
import androidx.compose.ui.platform.testTag
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

@Preview
@Composable
fun CharacterListScreenPreview() {
    val character = createEpisodesResult()
    val onItemClick: (RickyMortyEpisodesModel.Episode) -> Unit = { }
    EpisodesItem(episodes = character, onItemClick = onItemClick)
}
