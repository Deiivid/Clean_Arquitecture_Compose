package es.clean.architecture.ui.views.characters.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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