package com.example.rickymortydn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickymortydn.ui.character.screen.CharacterListScreen
import com.example.rickymortydn.ui.character.viewmodel.CharactersViewModel
import com.example.rickymortydn.ui.theme.RickyMortyDNTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyMortyDNTheme {
                // A surface container using the 'background' color from the theme
                /* Surface(
                     modifier = Modifier.fillMaxSize(),
                     color = MaterialTheme.colorScheme.background
                 ) {
                     MyScreenContent()
                 }*/
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "characterList") {
                    composable("characterList") {
                        CharacterListScreen(navController = navController)
                    }
                    composable("character_detail") {

                    }
                }
            }
        }
    }
}

@Composable
fun MyScreenContent() {
    // Obtén una instancia de tu ViewModel
    val charactersViewModel: CharactersViewModel = viewModel()

    MaterialTheme {
        Column {
            Button(
                onClick = {
                    // Llama a un método de tu ViewModel o accede a una propiedad
                    charactersViewModel.fetchCharacters()
                }
            ) {
                Text(text = "Haz algo")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickyMortyDNTheme {
        MyScreenContent()
    }
}