package es.clean.architecture.androidTest.cleanTest


import androidx.compose.ui.test.onAllNodesWithTag
import es.clean.architecture.ui.common.navigation.routes.Routes
import org.junit.Test

class LocationNavigationTest : BaseNavigationTest() {
    @Test
    fun navigateFromSplashToLocationScreen() {
        // Set up and navigate to the main screen with bottom navigation
        setUpNavigation()
        checkBottomNavigationExists()

        // Click the second item of the bottom navigation
        clickBottomNavigationItem(Routes.LocationListScreen.route)


        // Now continue with the specific steps for the "Character" test
        composeTestRule.waitUntil(
            timeoutMillis = 5000,
            condition = {
                // Check list has at least 1 item
                composeTestRule.onAllNodesWithTag("LocationItem").fetchSemanticsNodes()
                    .isNotEmpty()
            }
        )
    }
}
