package es.clean.architecture.data.remote.episodes.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteEpisodesModel(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<RemoteEpisode>
) : Parcelable {
    @Parcelize
    data class Info(
        @SerializedName("count")
        val count: Int,
        @SerializedName("next")
        val next: String,
        @SerializedName("pages")
        val pages: Int,
        @SerializedName("prev")
        val prev: String
    ) : Parcelable

    @Parcelize
    data class RemoteEpisode(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("characters")
        val characters: List<String>,
        @SerializedName("created")
        val created: String,
        @SerializedName("episode")
        val episode: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    ) : Parcelable
}