package es.clean.architecture.ui.views.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.clean.architecture.ui.common.Dimens.Custom55
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.ExtraSmall
import es.clean.architecture.ui.common.Dimens.Giant
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.Dimens.Medium
import es.clean.architecture.ui.common.navigation.navgraph.main.MainNavGraph
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomNavigationBar
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.theme.White
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

@Composable
fun CustomFloatingActionButton(
    navController: NavHostController,
    onShowDialogChange: (Boolean) -> Unit,
    isVisible: Boolean,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val pulseScale = infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    if (isVisible) {
        FloatingActionButton(
            onClick = {
                when (currentDestination?.route) {
                    BottomNavigationBar.Characters.route -> onShowDialogChange(true)
                    BottomNavigationBar.Episodes.route -> navController.navigate(Routes.CharacterList.route)
                }
            },
            contentColor = Color.White,
            modifier = Modifier
                .clip(CutCornerShape(Giant))
                .border(
                    width = ExtraSmall,
                    color = White,
                )
                .graphicsLayer {
                    scaleX = pulseScale.value
                    scaleY = pulseScale.value
                },
            containerColor = AppBackground,
            elevation = FloatingActionButtonDefaults.elevation(Medium),
            shape = CutCornerShape(Large)
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
fun CustomFloatingActionButtonClose(
    navController: NavHostController,
    onShowDialogChange: (Boolean) -> Unit,
    isVisible: Boolean,
    onSearchQueryReset: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val pulseScale = infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    if (isVisible) {
        FloatingActionButton(
            onClick = {
                when (currentDestination?.route) {
                    BottomNavigationBar.Characters.route -> {
                        onShowDialogChange(true)
                        onSearchQueryReset()
                    }

                    BottomNavigationBar.Episodes.route -> navController.navigate(Routes.CharacterList.route)
                }
            },
            contentColor = Color.White,
            modifier = Modifier
                .clip(CutCornerShape(Giant))
                .border(
                    width = ExtraSmall,
                    color = White,
                )
                .graphicsLayer {
                    scaleX = pulseScale.value
                    scaleY = pulseScale.value
                },
            containerColor = AppBackground,
            elevation = FloatingActionButtonDefaults.elevation(Medium),
            shape = CutCornerShape(Large)
        ) {
            Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}

@Composable
fun shouldShowFloatingActionButton(currentDestination: NavDestination?): Boolean {
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
        CustomBottomBar(navController, screens, currentDestination)
    }
}

@Composable
fun CustomBottomBar(
    navController: NavHostController,
    screens: List<BottomNavigationBar>,
    currentDestination: NavDestination?
) {
    Card(
        modifier = Modifier
            .testTag("BottomNavigation")
            .fillMaxWidth()
            .height(Custom55)
            .background(AppBackground),
        shape = RoundedCornerShape(
            topStart = ExtraLarge,
            topEnd = ExtraLarge
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = ExtraLarge)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
