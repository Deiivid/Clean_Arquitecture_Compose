package com.example.rickymortydn.data.remote.characters.models

import androidx.annotation.Keep
import com.example.rickymortydn.models.CharacterModel
import com.google.gson.annotations.SerializedName

@Keep
data class RemoteCharacterListModel(
    @SerializedName("info")
    val info: CharacterModel.Info,
    @SerializedName("results")
    val results: List<RemoteCharacterModel>?
)

