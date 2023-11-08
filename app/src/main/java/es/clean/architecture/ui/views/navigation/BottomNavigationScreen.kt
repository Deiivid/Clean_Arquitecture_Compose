package es.clean.architecture.ui.views.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.clean.architecture.R
import es.clean.architecture.ui.common.navigation.navgraph.main.MainNavGraph
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomNavigationBar
import es.clean.architecture.ui.common.navigation.routes.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    // offset(y = -tamaño del FAB / 2) para mover el FAB hacia arriba
                    .offset(y = 1.dp)
            ) {
                if (shouldShowFloatingActionButton(navController)) {

                    CustomFloatingActionButton(
                        onClick = {
                            when (currentDestination?.route) {
                                BottomNavigationBar.Characters.route -> {
                                    navController.navigate(Routes.LocationListScreen.route)
                                }

                                BottomNavigationBar.Episodes.route -> {
                                    navController.navigate(Routes.CharacterList.route)
                                }
                            }
                        },
                        isVisible = shouldShowFloatingActionButton(navController)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center, // Mantén esta línea para asegurarte de que el FAB sigue alineado en el eje x.

    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun CustomFloatingActionButton(onClick: () -> Unit, isVisible: Boolean) {
    if (isVisible) {
        FloatingActionButton(
            onClick = { onClick() },
            contentColor = Color.White,
            modifier = Modifier
                .clip(CutCornerShape(44.dp))
                .background(Color.Black),
            containerColor = colorResource(id = R.color.app_background),
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            shape = CutCornerShape(10.dp)

        ) {
            Icon(
                imageVector = Icons.Default.QuestionMark,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}

@Composable
fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(
        BottomNavigationBar.Characters,
        BottomNavigationBar.Episodes,
        BottomNavigationBar.Locations,
    )
    return screens.any { it.route == currentDestination?.route }
}

@Composable
fun shouldShowFloatingActionButton(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    return when (currentDestination?.route) {
        BottomNavigationBar.Characters.route -> true
        BottomNavigationBar.Episodes.route -> true
        else -> false
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavigationBar.Characters,
        BottomNavigationBar.Episodes,
        BottomNavigationBar.Locations,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavigationBar,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}