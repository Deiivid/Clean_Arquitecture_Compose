package es.clean.architecture.ui.views.characters.screens.search.customization

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import es.clean.architecture.R
import es.clean.architecture.ui.common.Dimens.Custom55
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.theme.ImageBackground


@SuppressLint("ResourceAsColor")
@Composable
fun StatusIconButton(
    icon: Painter,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    val iconSize = Custom55
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(ExtraLarge))
                .background(ImageBackground)
                .clickable(
                    onClick = onClick,
                    indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(Large)
        )
    }
}

@Composable
fun StatusIconRow(
) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        StatusIconButton(
            icon = painterResource(id = R.drawable.skull),
            onClick = { Toast.makeText(context, "DEAD", Toast.LENGTH_SHORT).show() })
        StatusIconButton(
            icon = painterResource(id = R.drawable.heartbeat),
            onClick = { Toast.makeText(context, "ALIVE", Toast.LENGTH_SHORT).show() })
        StatusIconButton(
            icon = painterResource(id = R.drawable.target),
            onClick = { Toast.makeText(context, "UNKNOWN", Toast.LENGTH_SHORT).show() })

    }
}


