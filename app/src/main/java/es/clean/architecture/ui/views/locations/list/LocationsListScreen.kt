package es.clean.architecture.ui.views.locations.list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import es.clean.architecture.R
import es.clean.architecture.domain.locations.models.RickyMortyLocationsModel
import es.clean.architecture.domain.locations.models.createLocationResult
import es.clean.architecture.ui.common.Dimens.Custom150
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.LottieErrorState
import es.clean.architecture.ui.common.LottieProgressBar
import es.clean.architecture.ui.common.Numbers.TWO
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.views.locations.viewmodel.LocationsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsListScreen(
    locationsViewModel: LocationsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val locations: LazyPagingItems<RickyMortyLocationsModel.Location> =
        locationsViewModel.allLocations.collectAsLazyPagingItems()

    when (locations.loadState.refresh) {
        is LoadState.Loading -> {
            LottieProgressBar()
        }

        is LoadState.NotLoading -> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier.fillMaxWidth(),
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
                        .background(AppBackground)
                        .padding(bottom = Large)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(TWO),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(
                            count = locations.itemCount,
                            key = locations.itemKey { location -> location.id }
                        ) { locationsIndex ->
                            locations[locationsIndex]?.let { item ->
                                LocationItem(
                                    location = item,
                                    { currentLocation ->
                                        Toast.makeText(
                                            context,
                                            "Has Pulsado en un elemento ${item.name}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        /* navController.currentBackStackEntry?.savedStateHandle?.set(
                                             LOCATION_OBJECT,
                                             value = currentLocation
                                         )
                                         navController.navigate(Routes.LocationDetailScreen.route)
                                         */
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

@Composable
fun LocationItem(
    location: RickyMortyLocationsModel.Location,
    onItemClick: (RickyMortyLocationsModel.Location) -> Unit,
) {
    val imageResId = when (location.name) {
        "Abadango" -> R.drawable.abadango
        "Earth" -> R.drawable.earth
        "Bird World" -> R.drawable.bird_world
        "Purge Planet" -> R.drawable.purge_planet
        "Citadel of Ricks" -> R.drawable.purge_planet
        "Alpha Centaurus" -> R.drawable.alpha
        "Worldender's lair" -> R.drawable.worldenders
        "Anatomy Park" -> R.drawable.anatomy
        "Interdimensional Cable" -> R.drawable.interdimensional
        "Immortality Field Resort" -> R.drawable.inmortality
        "Post Apocalyptic Earth" -> R.drawable.post_apocalyptic
        "Pizza Universe" -> R.drawable.pizza_universe
        "Prime Universe" -> R.drawable.prime_universe
        "Pluto" -> R.drawable.pluto
        "Scortia" -> R.drawable.scortia
        "Saturn" -> R.drawable.saturn
        "Shady Garage" -> R.drawable.shady
        "Smith Residence" -> R.drawable.smith
        "Solar System" -> R.drawable.solar
        "Gear World" -> R.drawable.gear

        else -> R.drawable.unknown
    }

    Box(
        modifier = Modifier
            .height(Custom150)
            .width(Custom150)
            .testTag("LocationItem") // Test
            .padding(Large)
            .clip(CircleShape)
            .background(Color.Transparent)
            .clickable { onItemClick(location) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .shadow(4.dp, CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = location.name,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun CharacterListScreenPreview() {
    val location = createLocationResult()
    val onItemClick: (RickyMortyLocationsModel.Location) -> Unit = { }
    LocationItem(location = location, onItemClick = onItemClick)
}
