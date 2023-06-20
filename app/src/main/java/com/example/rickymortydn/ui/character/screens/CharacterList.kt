package com.example.rickymortydn.ui.character.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickymortydn.ui.character.viewmodel.CharactersViewModel

@Composable
fun CharactersListScreen(
    navigationController: NavHostController,
) {
    // Obtén una instancia de tu ViewModel
    val charactersViewModel: CharactersViewModel = hiltViewModel()
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

@Composable
fun Screen1(navControler: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(text = "Pantalla1", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                //navControler.navigate(Routes.Pantalla2.route)
            })

    }
}