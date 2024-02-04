package es.clean.architecture.androidTest.fullCharacterTest

import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel.*
import es.clean.architecture.ui.aplication.MainActivity
import es.clean.architecture.ui.common.MAIN
import es.clean.architecture.ui.common.navigation.navgraph.root.RootNavigationGraph
import es.clean.architecture.ui.theme.CleanArchitectureComposeDNTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterDetailListScreenFullKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private var navController: NavHostController? by mutableStateOf(null)

    @Test
    fun navigationFromSplashToCharacterDetailScreen() {

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

        // Check bottom navigation
        composeTestRule.onNodeWithTag("BottomNavigation").assertExists()

        composeTestRule.waitUntil(
            timeoutMillis = 5000,
            condition = {
                //Check list has 1 item just for security
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