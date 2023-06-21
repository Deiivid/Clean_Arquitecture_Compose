package com.example.rickymortydn.ui.common.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    }
}