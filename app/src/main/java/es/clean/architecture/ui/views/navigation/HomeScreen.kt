package es.clean.architecture.ui.views.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.clean.architecture.ui.common.navigation.navgraph.main.MainNavGraph
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomNavigationBar
import es.clean.architecture.ui.views.characters.screens.search.CharacterSearchScreen
import es.clean.architecture.ui.views.navigation.customization.BottomBar
import es.clean.architecture.ui.views.navigation.customization.CustomFloatingActionButton
import es.clean.architecture.ui.views.navigation.customization.CustomFloatingActionButtonClose
import es.clean.architecture.ui.views.navigation.customization.shouldShowFloatingActionButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    var showDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(navController.currentBackStackEntryAsState().value?.destination?.route) {
        if (navController.currentDestination?.route == BottomNavigationBar.Characters.route) {
            searchQuery = null
        }
    }

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = {
            if (searchQuery.isNullOrEmpty()) {
                CustomFloatingActionButton(
                    onShowDialogChange = { showDialog = it },
                    isVisible = shouldShowFloatingActionButton(
                        navController.currentBackStackEntryAsState().value?.destination
                    ),
                    navController = navController
                )
            } else {
                CustomFloatingActionButtonClose(
                    onShowDialogChange = { showDialog = it },
                    isVisible = shouldShowFloatingActionButton(
                        navController.currentBackStackEntryAsState().value?.destination
                    ),
                    navController = navController,
                    onSearchQueryReset = { searchQuery = "" }

                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        MainNavGraph(navController = navController, searchQuery = searchQuery)
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            CharacterSearchScreen(
                onSearchComplete = { query ->
                    searchQuery = query
                    showDialog = false
                    navController.navigate(BottomNavigationBar.Characters.route)
                }
            )
        }
    }
}
