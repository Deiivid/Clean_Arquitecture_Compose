package es.clean.architecture.data.remote.characters.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class RemoteCharacterModel(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val remoteLocation: RemoteLocation,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val remoteOrigin: RemoteOrigin,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String
) : Parcelable

@Keep
@Parcelize
data class RemoteLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable

@Keep
@Parcelize
data class RemoteOrigin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable

