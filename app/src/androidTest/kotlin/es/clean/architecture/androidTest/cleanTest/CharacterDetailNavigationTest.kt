package es.clean.architecture.androidTest.cleanTest

import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Test

class CharacterNavigationTest : BaseNavigationTest() {

    @Test
    fun navigateFromSplashToCharacterDetailScreen() {
        // Navigate to the main screen with bottom navigation
        setUpNavigation()
        checkBottomNavigationExists()

        // Now continue with the specific steps for the "Character" test
        composeTestRule.waitUntil(
            timeoutMillis = 5000,
            condition = {
                // Check list has at least 1 item
                composeTestRule.onAllNodesWithTag("CharacterItem").fetchSemanticsNodes()
                    .isNotEmpty()
            }
        )

        // Select 1 item of the list
        val firstItem = composeTestRule.onAllNodesWithTag("CharacterItem").onFirst()
        firstItem.performClick()

        // Check that detail screen is shown
        composeTestRule.onNodeWithTag("CharacterDetailScreen").assertExists()
    }
}
