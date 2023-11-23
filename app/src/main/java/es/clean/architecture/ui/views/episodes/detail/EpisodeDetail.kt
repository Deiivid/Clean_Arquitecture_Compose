package es.clean.architecture.ui.views.episodes.detail

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.models.character.createCharacterResult
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.domain.episodes.models.createEpisodesResult

@ExperimentalCoilApi
@Composable
fun EpisodeDetail(
    rickyMortyEpisode: RickyMortyEpisodesModel.Episode //We receive the data
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
                                rickyMortyEpisode.name,
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
                                rickyMortyEpisode.airDate,
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
                                rickyMortyEpisode.created,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))


                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun CharacterDetailScreenPreview() {
    val episode = createEpisodesResult()
    EpisodeDetail(rickyMortyEpisode = episode)
}
