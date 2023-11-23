package es.clean.architecture.domain.episodes.models


import android.os.Parcelable
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class RickyMortyEpisodesModel(
    val info: Info,
    val results: List<Episode>
) : Parcelable {
    @Parcelize
    data class Info(
        val count: Int,
        val next: String,
        val pages: Int,
        val prev: String
    ) : Parcelable

    @Parcelize
    data class Episode(
        val airDate: String,
        val characters: List<String>,
        val created: String,
        val episode: String,
        val id: Int,
        val name: String,
        val url: String
    ) : Parcelable
}


/**
 * We use this section to initialize the detail screen to get the preview
 */
fun createEpisodesResult(): RickyMortyEpisodesModel.Episode {
    return RickyMortyEpisodesModel.Episode(
        airDate = "2023-10-25",
        characters = listOf("Episode 1", "Episode 2"),
        created = "Male",
        episode = "episodio pruebas",
        id = 1,
        name = "nombre pruebas",
        url = "https://pruebas"
    )
}
