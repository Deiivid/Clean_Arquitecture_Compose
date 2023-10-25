package com.example.rickymortydn.ui.common.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickymortydn.models.CharacterModel
import com.example.rickymortydn.ui.character.detail.screen.CharacterDetailScreen
import com.example.rickymortydn.ui.character.screens.CharactersListScreen
import com.example.rickymortydn.ui.common.navigation.Routes
import com.example.rickymortydn.ui.splash.screen.AnimatedSplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(route = Routes.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Routes.CharacterList.route) {
            CharactersListScreen(navController = navController)
        }
        //pasar datos parcelables

        composable(route = Routes.CharacterDetailScreen.route) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<CharacterModel.CharacterResult>("character")
            result?.let {
                CharacterDetailScreen(navController = navController, character = it)
            }
        }
    }
}