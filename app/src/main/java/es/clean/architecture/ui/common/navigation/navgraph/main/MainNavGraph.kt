package es.clean.architecture.ui.common.navigation.navgraph.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import es.clean.architecture.models.CharacterModel
import es.clean.architecture.ui.character.CharactersListScreen
import es.clean.architecture.ui.character.screens.detail.CharacterDetailScreen
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomBarScreen
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.episodes.EpisodesScreen
import es.clean.architecture.ui.locations.LocationsScreen


@OptIn(ExperimentalCoilApi::class)
@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.CharacterList.route
    ) {
        //region [CHARACTERS]
        composable(route = Routes.CharacterList.route) {
            CharactersListScreen(navController = navController)
        }
        //charactersNavGraph(navController = navController)
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
                CharacterDetailScreen(character = it)
            }
        }
        //endregion [CHARACTERS]

        composable(route = BottomBarScreen.Episodes.route) {
            EpisodesScreen()
        }
        composable(route = BottomBarScreen.Locations.route) {
            LocationsScreen()
        }
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