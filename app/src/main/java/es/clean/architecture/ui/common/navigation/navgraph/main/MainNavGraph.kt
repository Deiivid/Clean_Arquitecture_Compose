package es.clean.architecture.ui.common.navigation.navgraph.main

//import es.clean.architecture.ui.views.characters.CharactersListScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.domain.locations.models.RickyMortyLocationsModel
import es.clean.architecture.ui.common.CHARACTER_OBJECT
import es.clean.architecture.ui.common.EPISODE_OBJECT
import es.clean.architecture.ui.common.LOCATION_OBJECT
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomNavigationBar
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.views.characters.screens.detail.CharacterDetailScreen
import es.clean.architecture.ui.views.characters.screens.list.CharactersListScreen
import es.clean.architecture.ui.views.episodes.detail.EpisodeDetailScreen
import es.clean.architecture.ui.views.episodes.list.EpisodesListScreen
import es.clean.architecture.ui.views.locations.LocationsScreen


@OptIn(ExperimentalCoilApi::class)
@Composable
fun MainNavGraph(navController: NavHostController, searchQuery: String?) {
    NavHost(
        navController = navController,
        startDestination = Routes.CharacterList.route
    ) {
        //region [CHARACTERS]
        composable(route = BottomNavigationBar.Characters.route) {
            CharactersListScreen(navController = navController, searchQuery = searchQuery ?: "")

        }

        /**
         * To pass data we need first start with parcelable, set this in the gradle.app plugin section
         * and then be careful in the screen use currentBackStackEntry and here previousBackStackEntry
         **/
        composable(route = Routes.CharacterDetailScreen.route) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<RickyMortyCharacterModel.RickyMortyCharacter>(
                    CHARACTER_OBJECT
                )
            result?.let {
                CharacterDetailScreen(rickyMortyCharacter = it)
            }
        }
        //endregion [CHARACTERS]

        //region [EPISODES]

        composable(route = BottomNavigationBar.Episodes.route) {
            EpisodesListScreen(navController = navController)
        }

        composable(route = Routes.EpisodeDetailScreen.route) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<RickyMortyEpisodesModel.Episode>(
                    EPISODE_OBJECT
                )
            result?.let {
                EpisodeDetailScreen(rickyMortyEpisode = it)
            }
        }
        //endregion [EPISODES]
        //region [LOCATIONS]
        composable(route = BottomNavigationBar.Locations.route) {
            LocationsScreen()
        }
        composable(route = Routes.LocationDetailScreen.route) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<RickyMortyLocationsModel.Location>(
                    LOCATION_OBJECT
                )
            result?.let {
                //  LocationDetailScreen(rickyMortyLocation = it)
            }
        }
        //endregion [LOCATIONS]

    }
}


//region [Character]
/*fun NavGraphBuilder.charactersNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        /**
         * To pass data we need first start with parcelable, set this in the gradle.app plugin section
         * and then be careful in the screen use currentBackStackEntry and here previousBackStackEntry
         **/
        composable(route = Routes.CharacterDetailScreen.route) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<CharacterModel.CharacterResult>(
                    "character"
                )
            result?.let {
                CharacterDetailScreen(navController = navController, character = it)
            }
        }
    }
}
//endregion [Character]

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}
*/

