package es.clean.architecture.ui.common.navigation.navgraph.main.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.rounded.Stars
import androidx.compose.ui.graphics.vector.ImageVector
import es.clean.architecture.ui.common.navigation.routes.Routes


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Characters : BottomBarScreen(
        route = Routes.CharacterList.route,
        title = "CHARACTERS",
        icon = Icons.Rounded.Stars
    )

    data object Episodes : BottomBarScreen(
        route = Routes.EpisodeListScreen.route,
        title = "EPISODES",
        icon = Icons.Default.RocketLaunch
    )

    data object Locations : BottomBarScreen(
        route = Routes.LocationListScreen.route,
        title = "LOCATIONS",
        icon = Icons.Default.Explore
    )
}
