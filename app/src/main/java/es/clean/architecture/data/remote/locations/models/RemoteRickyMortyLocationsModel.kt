package es.clean.architecture.data.remote.locations.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class RemoteRickyMortyLocationsModel(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<RemoteLocation>
): Parcelable {
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
    ): Parcelable
    @Parcelize
    data class RemoteLocation(
        @SerializedName("created")
        val created: String,
        @SerializedName("dimension")
        val dimension: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("residents")
        val residents: List<String>,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
    ): Parcelable
}
