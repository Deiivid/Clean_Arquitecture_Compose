package es.clean.architecture.domain.characters.models.episodes


import android.os.Parcelable
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