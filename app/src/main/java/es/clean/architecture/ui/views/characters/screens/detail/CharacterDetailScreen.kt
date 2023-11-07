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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import es.clean.architecture.R
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.models.character.createCharacterResult
import es.clean.architecture.ui.views.characters.common.getStatusIconWithTint

class CutCornersShapeCustom(private val bigCut: Dp) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val bigCutSize = bigCut.toPx(density)
        val path = Path().apply {
            lineTo(size.width - bigCutSize, 0f)
            lineTo(size.width, bigCutSize)
            lineTo(size.width, size.height)
            lineTo(bigCutSize, size.height)
            lineTo(0f, size.height - bigCutSize)
            close()
        }
        return Outline.Generic(path)
    }

}

private fun Dp.toPx(density: Density): Float = this.value * density.density

@Composable
fun CharacterDetailScreen(rickyMortyCharacter: RickyMortyCharacterModel.RickyMortyCharacter) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, bottom = 55.dp, start = 24.dp, end = 24.dp)
                .background(
                    color = colorResource(id = R.color.card_border),
                    shape = CutCornersShapeCustom(38.dp)
                )

                .clip(
                    CutCornerShape(
                        topStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .padding(2.dp)
                .background(
                    color = colorResource(
                        R.color.card_background
                    ),
                    shape = CutCornersShapeCustom(40.dp)
                )
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.border),
                    shape = CutCornersShapeCustom(42.dp)
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val (statusIcon, statusTint) = getStatusIconWithTint(rickyMortyCharacter.status)
                    Icon(
                        modifier = Modifier
                            .height(35.dp)
                            .width(40.dp)
                            .padding(start = 4.dp, end = 6.dp),
                        painter = painterResource(id = statusIcon),
                        contentDescription = "Status Icon",
                        tint = statusTint
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = rickyMortyCharacter.status,
                        fontWeight = Bold,
                        color = Color.White,
                        fontSize = 22.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(rickyMortyCharacter.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = rickyMortyCharacter.image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(CutCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = rickyMortyCharacter.name,
                    color = Color.White,
                    fontWeight = Bold,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                EpisodesGrid(rickyMortyCharacter.episode)

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.White)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Origin: ${rickyMortyCharacter.characterOrigin.name}",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Location: ${rickyMortyCharacter.characterLocation.name}",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .background(
                colorResource(id = R.color.card_border),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = Bold,
            fontSize = 14.sp,
            maxLines = 1
        )
    }
}

@Composable
fun EpisodesGrid(episodes: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
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
    CharacterDetailScreen(rickyMortyCharacter = character)
}
