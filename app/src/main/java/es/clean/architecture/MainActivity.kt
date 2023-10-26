package es.clean.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import es.clean.architecture.ui.common.navigation.navgraph.SetupNavGraph
import es.clean.architecture.ui.theme.CleanArchitectureComposeDNTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureComposeDNTheme {
                // A surface container using the 'background' color from the theme
                /*  Surface(
                      modifier = Modifier.fillMaxSize(),
                      color = MaterialTheme.colorScheme.background
                  ) {
                         val navigationController = rememberNavController()
                         NavHost(
                             navController = navigationController,
                             startDestination = Routes.CharacterList.route
                         ) {
                             composable(Routes.CharacterList.route) { CharactersListScreen(navigationController) }
                         }
                     */

                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanArchitectureComposeDNTheme {

    }
}