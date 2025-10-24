package es.clean.architecture.ui.views.characters.screens.detail.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.Medium
import es.clean.architecture.ui.common.Dimens.Tiny
import es.clean.architecture.ui.common.Numbers.ONE
import es.clean.architecture.ui.common.TextSizes.Sp14
import es.clean.architecture.ui.theme.Border
import es.clean.architecture.ui.theme.CardBorder
import es.clean.architecture.ui.views.characters.common.CutCornersCustom

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
