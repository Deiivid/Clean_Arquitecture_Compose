package es.clean.architecture.ui.views.characters.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.models.character.createCharacterResult
import es.clean.architecture.ui.common.Dimens.Custom1
import es.clean.architecture.ui.common.Dimens.Custom350
import es.clean.architecture.ui.common.Dimens.Custom55
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.ExtraSmall
import es.clean.architecture.ui.common.Dimens.Giant
import es.clean.architecture.ui.common.Dimens.Huge
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.Dimens.Massive
import es.clean.architecture.ui.common.Dimens.Medium
import es.clean.architecture.ui.common.Dimens.Small
import es.clean.architecture.ui.common.Dimens.Tiny
import es.clean.architecture.ui.common.Numbers.ONE
import es.clean.architecture.ui.common.TextSizes.Sp12
import es.clean.architecture.ui.common.TextSizes.Sp14
import es.clean.architecture.ui.common.TextSizes.Sp22
import es.clean.architecture.ui.common.TextSizes.Sp26
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.theme.Border
import es.clean.architecture.ui.theme.CardBackground
import es.clean.architecture.ui.theme.CardBorder
import es.clean.architecture.ui.views.characters.common.CutCornersCustom
import es.clean.architecture.ui.views.characters.common.getStatusIconWithTint
import es.clean.architecture.ui.views.characters.screens.detail.chip.Chip

@Composable
fun CharacterDetail(rickyMortyCharacter: RickyMortyCharacterModel.RickyMortyCharacter) {
    Box(
        modifier = Modifier
            .testTag("CharacterDetailScreen")
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Custom55, bottom = Custom55, start = Huge, end = Huge)
                .background(
                    color = CardBorder,
                    shape = CutCornersCustom(Giant)
                )
                .clip(
                    CutCornerShape(
                        topStart = Giant,
                        bottomEnd = Giant
                    )
                )
                .padding(Tiny)
                .background(
                    color = CardBackground,
                    shape = CutCornersCustom(Giant)
                )
                .border(
                    width = Tiny,
                    color = Border,
                    shape = CutCornersCustom(Giant)
                )
                .padding(ExtraLarge)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ExtraSmall)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val (statusIcon, statusTint) = getStatusIconWithTint(rickyMortyCharacter.status)
                    Icon(
                        modifier = Modifier
                            .height(Massive)
                            .width(Giant)
                            .padding(start = ExtraSmall, end = Small),
                        painter = painterResource(id = statusIcon),
                        contentDescription = "Status Icon",
                        tint = statusTint
                    )
                    Spacer(modifier = Modifier.width(ExtraSmall))
                    Text(
                        text = rickyMortyCharacter.status,
                        fontWeight = Bold,
                        color = Color.White,
                        fontSize = Sp22
                    )
                }
                Spacer(modifier = Modifier.height(Medium))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(rickyMortyCharacter.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = rickyMortyCharacter.image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Custom350)
                        .clip(CutCornerShape(ExtraLarge))
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(Large))

                Text(
                    text = rickyMortyCharacter.name,
                    color = Color.White,
                    fontWeight = Bold,
                    fontSize = Sp26
                )
                Spacer(modifier = Modifier.height(Small))
                EpisodesGrid(rickyMortyCharacter.episode)

                Spacer(modifier = Modifier.height(Large))
                HorizontalDivider(thickness = Custom1, color = Color.White)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Origin: ${rickyMortyCharacter.characterOrigin.name}",
                        color = Color.White,
                        fontSize = Sp12
                    )
                    Text(
                        text = "Location: ${rickyMortyCharacter.characterLocation.name}",
                        color = Color.White,
                        fontSize = Sp12
                    )
                }
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    val chipShape = CutCornersCustom(ExtraLarge)
    Box(
        modifier = Modifier
            .padding(end = Medium)
            .clip(chipShape)
            .background(CardBorder)
            .border(Tiny, Border, chipShape)
            .padding(horizontal = ExtraLarge, vertical = Medium)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = Bold,
            fontSize = Sp14,
            maxLines = ONE
        )
    }
}

@Composable
fun EpisodesGrid(episodes: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Medium),
        contentPadding = PaddingValues(horizontal = Medium, vertical = ExtraLarge)
    ) {
        items(episodes) { episode ->
            val episodeNumber = episode.substringAfterLast("/")
            Chip(text = "Ep. $episodeNumber")
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun CharacterDetailScreenPreview() {
    val character = createCharacterResult()
    CharacterDetail(rickyMortyCharacter = character)
}
