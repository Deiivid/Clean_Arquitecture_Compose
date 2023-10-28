package es.clean.architecture.ui.character.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import es.clean.architecture.models.CharacterModel
import es.clean.architecture.models.createCharacterResult

@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    character: CharacterModel.CharacterResult //We receive the data
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberImagePainter(data = character.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 1.dp)
                        .background(Color.White)
                )
                /*Image(
                    painter = painterResource(R.drawable.test_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 1.dp)
                        .background(Color.White)
                )*/
            }

            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Transparent)
                        .shadow(elevation = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .shadow(elevation = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(6.dp),
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.Center

                            ) {
                                Icon(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .align(CenterVertically),
                                    imageVector = Icons.Outlined.DarkMode,
                                    contentDescription = null,
                                    tint = Color.Black,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    character.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center

                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .shadow(elevation = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(6.dp),
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.Center

                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.EmojiPeople,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    character.gender,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center

                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .shadow(elevation = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(6.dp),
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.GraphicEq,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    character.species,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center

                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .shadow(elevation = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(6.dp),
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                val iconColor = if (character.status != "Alive") {
                                    Color.Red
                                } else {
                                    Color.Black
                                }
                                Icon(
                                    imageVector = Icons.Default.LocalHospital,
                                    contentDescription = null,
                                    tint = iconColor
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    character.status,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun CharacterDetailScreenPreview() {
    val character = createCharacterResult()
    CharacterDetailScreen(character = character)
}
