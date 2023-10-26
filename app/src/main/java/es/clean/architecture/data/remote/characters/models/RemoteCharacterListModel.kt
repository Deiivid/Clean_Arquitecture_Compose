package es.clean.architecture.data.remote.characters.models

import androidx.annotation.Keep
import es.clean.architecture.models.CharacterModel
import com.google.gson.annotations.SerializedName

@Keep
data class RemoteCharacterListModel(
    @SerializedName("info")
    val info: CharacterModel.Info,
    @SerializedName("results")
    val results: List<RemoteCharacterModel>?
)

