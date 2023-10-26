package es.clean.architecture.ui.character.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import es.clean.architecture.models.CharacterModel
import es.clean.architecture.models.createCharacterResult
import es.clean.architecture.ui.common.navigation.Routes

@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    character: CharacterModel.CharacterResult //We receive the data
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                Text(
                    character.name,
                    modifier = Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    character.gender,
                    modifier = Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp

                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .align(CenterHorizontally)
                        .size(height = 600.dp, width = 500.dp)
                        .clickable {
                            navController.navigate(Routes.CharacterList.route)
                        },
                    contentAlignment = Alignment.Center,
                    content = {
                        Image(
                            painter = rememberImagePainter(data = character.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White)
                        )
                        /*Image(
                            painter = painterResource(R.drawable.ic_test),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White)
                        )*/
                    }
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun CharacterListScreenPreview() {
    val navController = rememberNavController()
    val character = createCharacterResult()
    CharacterDetailScreen(navController, character = character)
}
