package com.example.rickymortydn.models


import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class CharacterModel(
    val info: Info,
    val characterResults: List<CharacterResult>
) {
    @Keep
    data class Info(
        val count: Int,
        val next: String,
        val pages: Int,
        val prev: Any
    ) : Serializable

    @Keep
    data class CharacterResult(
        val created: String,
        val episode: List<String>,
        val gender: String,
        val id: Int,
        val image: String,
        val characterLocation: CharacterLocation,
        val name: String,
        val characterOrigin: CharacterOrigin,
        val species: String,
        val status: String,
        val type: String
    ) : Serializable {
        @Keep
        data class CharacterLocation(
            val name: String,
            val url: String
        ) : Serializable

        @Keep
        data class CharacterOrigin(
            val name: String,
            val url: String
        ) : Serializable
    }
}