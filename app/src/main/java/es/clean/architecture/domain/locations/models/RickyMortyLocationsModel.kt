package es.clean.architecture.domain.locations.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RickyMortyLocationsModel(
    val info: Info,
    val results: List<Location>
) : Parcelable {
    @Parcelize
    data class Info(
        val count: Int,
        val next: String,
        val pages: Int,
        val prev: String
    ) : Parcelable

    @Parcelize
    data class Location(
        val created: String,
        val dimension: String,
        val id: Int,
        val name: String,
        val residents: List<String>,
        val type: String,
        val url: String
    ) : Parcelable
}

/**
 * We use this section to initialize the detail screen to get the preview
 */
fun createLocationResult(): RickyMortyLocationsModel.Location {
    return RickyMortyLocationsModel.Location(
        created = "2023-10-25",
        dimension = "pruebas",
        id = 1,
        name = "Character Name",
        residents = listOf("Location 1", "Location 2"),
        type = "Human",
        url = "Alive"
    )
}
