package es.clean.architecture.ui.views.navigation.customization

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import es.clean.architecture.ui.common.Dimens.ExtraSmall
import es.clean.architecture.ui.common.Dimens.Giant
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.Dimens.Medium
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomNavigationBar
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.theme.White


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
        ), label = ""
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
        ), label = ""
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
