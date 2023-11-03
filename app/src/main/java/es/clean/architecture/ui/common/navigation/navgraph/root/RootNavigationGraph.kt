package es.clean.architecture.ui.common.navigation.navgraph.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import es.clean.architecture.ui.common.MAIN
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.views.navigation.HomeScreen
import es.clean.architecture.ui.views.splash.AnimatedSplashScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route,
    ) {
        composable(route = Routes.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = MAIN) {
            HomeScreen()
        }
    }
}
