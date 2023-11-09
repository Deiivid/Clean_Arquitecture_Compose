package es.clean.architecture.ui.views.characters.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CharacterSearchScreen() {
    Column(
        modifier = Modifier.fillMaxSize(
        ).padding(16.dp)
    ) {
    }

}

@Preview
@Composable
fun ShowCharacterSearchScreen() {
    CharacterSearchScreen()
}