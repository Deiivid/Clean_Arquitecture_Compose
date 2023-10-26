package es.clean.architecture.data.remote.characters.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
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
) : Serializable

@Keep
data class RemoteLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Serializable

@Keep
data class RemoteOrigin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Serializable

