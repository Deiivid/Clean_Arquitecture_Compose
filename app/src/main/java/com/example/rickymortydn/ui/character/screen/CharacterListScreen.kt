package com.example.rickymortydn.ui.character.screen

import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.rickymortydn.ui.character.viewmodel.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    navController: NavController,
    //charactersViewmodel:CharactersViewModel = hiltViewModel(),
    //onItemClicked:(characterId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ){
        //region Variables
        val snackbarHostState = remember { SnackbarHostState() }

        //endregion Variables


        Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ){

            }
        }
    }
}