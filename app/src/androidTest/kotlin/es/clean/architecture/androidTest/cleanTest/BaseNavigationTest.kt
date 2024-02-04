package es.clean.architecture.androidTest.cleanTest

import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import es.clean.architecture.ui.aplication.MainActivity
import es.clean.architecture.ui.common.MAIN
import es.clean.architecture.ui.common.navigation.navgraph.root.RootNavigationGraph
import es.clean.architecture.ui.theme.CleanArchitectureComposeDNTheme
import org.junit.Rule

open class BaseNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private var navController: NavHostController? by mutableStateOf(null)

    fun setUpNavigation() {
        composeTestRule.activity.setContent {
            navController = rememberNavController()
            CleanArchitectureComposeDNTheme {
                RootNavigationGraph(navController = navController!!)
            }
        }
        composeTestRule.waitUntil(
            timeoutMillis = 5000,
            condition = { navController?.currentDestination?.route == MAIN }
        )
    }

    fun checkBottomNavigationExists() {
        // Check bottom navigation is present
        composeTestRule.onNodeWithTag("BottomNavigation").assertExists()
    }

    fun clickBottomNavigationItem(tag: String) {
        composeTestRule.onNodeWithTag(tag).performClick()
    }
}
