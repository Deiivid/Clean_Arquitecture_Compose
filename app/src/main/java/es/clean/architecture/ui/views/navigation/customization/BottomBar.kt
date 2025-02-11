package es.clean.architecture.ui.views.navigation.customization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import es.clean.architecture.ui.common.Dimens.Custom55
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.navigation.navgraph.main.screen.BottomNavigationBar
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.views.navigation.AddItem


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
            topStart = ExtraLarge, topEnd = ExtraLarge
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
