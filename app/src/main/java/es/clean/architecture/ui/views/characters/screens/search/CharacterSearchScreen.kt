package es.clean.architecture.ui.views.characters.screens.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.clean.architecture.R

@Composable
fun CharacterSearchScreen() {
    Column(
        modifier = Modifier.fillMaxSize(
        ).padding(16.dp)
    ) {
        BasicTextField(
            value = "prueba",
            onValueChange = {/* actualiza tu estado aquí */},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
                .padding(16.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            IconButton(onClick = { /* tu función de filtro aquí */ }) {
                Icon(painter= painterResource(id = R.drawable.heartbeat), contentDescription = "Alive")
            }
            IconButton(onClick = { /* tu función de filtro aquí */ }) {
                Icon(painter= painterResource(id = R.drawable.skull), contentDescription = "Dead")
            }
            IconButton(onClick = { /* tu función de filtro aquí */ }) {
                Icon(painter= painterResource(id = R.drawable.target), contentDescription = "Unknown")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value ="personaje",
            onValueChange = {/* actualiza tu estado aquí */},
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
                .padding(16.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))


        BasicTextField(
            value = "estado",
            onValueChange = {/* actualiza tu estado aquí */},
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
                .padding(16.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Preview
@Composable
fun ShowCharacterSearchScreen() {
    CharacterSearchScreen()
}