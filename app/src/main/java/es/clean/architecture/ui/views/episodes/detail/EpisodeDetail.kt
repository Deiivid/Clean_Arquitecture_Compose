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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.domain.episodes.models.createEpisodesResult
import es.clean.architecture.ui.common.Dimens.Custom60
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.ExtraSmall
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.Dimens.Medium
import es.clean.architecture.ui.common.Dimens.Small
import es.clean.architecture.ui.common.Dimens.Tiny
import es.clean.architecture.ui.common.TextSizes.Sp20

@ExperimentalCoilApi
@Composable
fun EpisodeDetail(
    rickyMortyEpisode: RickyMortyEpisodesModel.Episode
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .testTag("EpisodeDetailScreen"),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Large))
                    .background(Color.Transparent)
                    .shadow(elevation = ExtraSmall),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(ExtraLarge)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Small))
                            .shadow(elevation = Tiny),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Custom60)
                                .padding(Small),
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
                            Spacer(modifier = Modifier.width(Medium))
                            Text(
                                rickyMortyEpisode.name,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = Sp20,
                                textAlign = TextAlign.Center

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(Medium))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Small))
                            .shadow(elevation = Tiny),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Custom60)
                                .padding(Small),
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Icon(
                                imageVector = Icons.Outlined.EmojiPeople,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(Medium))
                            Text(
                                rickyMortyEpisode.airDate,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = Sp20,
                                textAlign = TextAlign.Center

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(Medium))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Small))
                            .shadow(elevation = Tiny),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Custom60)
                                .padding(Small),
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.GraphicEq,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(Medium))
                            Text(
                                rickyMortyEpisode.created,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = Sp20,
                                textAlign = TextAlign.Center

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(Medium))


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
