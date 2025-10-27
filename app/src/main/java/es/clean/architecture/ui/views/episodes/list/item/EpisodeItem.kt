package es.clean.architecture.ui.views.episodes.list.item

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.ui.common.Dimens.Custom160
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.Dimens.Massive
import es.clean.architecture.ui.common.Dimens.Medium
import es.clean.architecture.ui.common.Dimens.Small
import es.clean.architecture.ui.common.Dimens.Tiny
import es.clean.architecture.ui.common.Numbers.FIVE_HUNDRED
import es.clean.architecture.ui.common.Numbers.ONE
import es.clean.architecture.ui.common.Numbers.SIXTEEN
import es.clean.architecture.ui.common.Numbers.TWO
import es.clean.architecture.ui.common.TextSizes.Sp12
import es.clean.architecture.ui.common.TextSizes.Sp14
import es.clean.architecture.ui.common.TextSizes.Sp16
import es.clean.architecture.ui.theme.AppBackground
import es.clean.architecture.ui.views.episodes.formatter.formatEmissionDate


@SuppressLint("ResourceAsColor")
@Composable
fun EpisodesItem(
    episodes: RickyMortyEpisodesModel.Episode,
    onItemClick: (rickyMortyEpisode: RickyMortyEpisodesModel.Episode) -> Unit,
) {
    val borderWidth = TWO.dp
    val borderColor = Color.White

    val scale = remember { Animatable(1f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = FIVE_HUNDRED, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    val itemHeight = Custom160
    Box(
        modifier = Modifier
            .padding(top = ExtraLarge)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Surface(
            shape = RoundedCornerShape(Medium),
            color = Color(0xFFDAE1E7),
            modifier = Modifier
                .clickable { onItemClick(episodes) }
                .height(itemHeight)
                .padding(Medium)
                .border(borderWidth, borderColor, shape = RoundedCornerShape(Medium)),
            shadowElevation = Tiny
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Medium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scale.value)
                ) {
                    Text(
                        text = episodes.name,
                        fontSize = Sp16,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        maxLines = TWO,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(ExtraLarge)
                    )
                    Spacer(modifier = Modifier.width(Small))
                    Text(
                        text = formatEmissionDate(episodes.airDate),
                        fontSize = Sp14,
                        maxLines = ONE,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = Color.DarkGray,
                        modifier = Modifier.size(ExtraLarge)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = episodes.url,
                        fontSize = Sp12,
                        maxLines = ONE,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Surface(
            shape = CircleShape,
            color = AppBackground,
            modifier = Modifier
                .size(Massive)
                .offset(y = (-SIXTEEN).dp)
                .align(Alignment.TopCenter)
                .border(borderWidth, borderColor, shape = RoundedCornerShape(Large))

        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = episodes.id.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = Sp16,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
