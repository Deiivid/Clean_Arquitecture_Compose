package es.clean.architecture.ui.views.characters.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import es.clean.architecture.R

@Composable
fun getStatusIconWithTint(status: String): Pair<Int, Color> {
    return when (status) {
        "Alive" -> Pair(R.drawable.heartbeat, Color.Unspecified)
        "unknown" -> Pair(R.drawable.target, Color.Black)
        "Dead" -> Pair(R.drawable.skull, Color.Gray)
        else -> Pair(R.drawable.skull, Color.Gray)
    }
}

class CutCornersCustom(private val bigCut: Dp) : Shape {
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
