package es.clean.architecture.ui.views.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomNavigationBar

@Composable
fun RowScope.AddItem(
    screen: BottomNavigationBar,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val scale by animateFloatAsState(if (selected) 1.1f else 1f, label = "")
    val tint by animateColorAsState(if (selected) Color.Black else Color.Black, label = "")

    NavigationBarItem(

        label = {
            Text(text = screen.title, color = tint)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = tint
            )
        },
        selected = selected,
        onClick = {

            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier
            .testTag(screen.route)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .animateContentSize()
    )
}