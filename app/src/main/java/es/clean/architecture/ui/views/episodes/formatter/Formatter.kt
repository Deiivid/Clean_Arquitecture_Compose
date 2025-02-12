package es.clean.architecture.ui.views.episodes.formatter

import java.text.SimpleDateFormat
import java.util.Locale

fun formatEmissionDate(dateString: String): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val parsedDate = parser.parse(dateString)
        formatter.format(parsedDate)
    } catch (ex: Exception) {
        dateString
    }
}