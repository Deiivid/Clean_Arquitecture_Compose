package es.clean.architecture.ui.common.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.clean.architecture.models.CharacterModel
import es.clean.architecture.ui.character.screens.detail.CharacterDetailScreen
import es.clean.architecture.ui.character.screens.list.CharactersListScreen
import es.clean.architecture.ui.common.navigation.Routes
import es.clean.architecture.ui.splash.screen.AnimatedSplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(route = Routes.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Routes.CharacterList.route) {
            CharactersListScreen(navController = navController)
        }


        /**
         * To pass data we need first start with parcelable, set this in the gradle.app plugin section and then be careful in the screen use currentBackStackEntry and here previousBackStackEntry
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