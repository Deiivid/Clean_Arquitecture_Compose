package com.example.rickymortydn.models


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Keep
data class CharacterModel(
    val info: Info,
    val characterResults: List<CharacterResult>
) {
    @Keep
    @Parcelize
    data class Info(
        val count: Int,
        val next: String,
        val pages: Int,
        val prev: String
    ) : Parcelable

    @Keep
    @Parcelize
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
    ) : Parcelable {
        @Keep
        @Parcelize
        data class CharacterLocation(
            val name: String,
            val url: String
        ) : Parcelable

        @Keep
        @Parcelize
        data class CharacterOrigin(
            val name: String,
            val url: String
        ) : Parcelable
    }
}




fun createCharacterResult(): CharacterModel.CharacterResult {
    // Creación de una instancia de CharacterLocation
    val characterLocation = CharacterModel.CharacterResult.CharacterLocation(
        name = "Earth",
        url = "https://example.com/earth"
    )

    // Creación de una instancia de CharacterOrigin
    val characterOrigin = CharacterModel.CharacterResult.CharacterOrigin(
        name = "Unknown",
        url = "https://example.com/unknown"
    )

    // Creación de una instancia de CharacterResult y devolución
    return CharacterModel.CharacterResult(
        created = "2023-10-25",
        episode = listOf("Episode 1", "Episode 2"),
        gender = "Male",
        id = 1,
        image = "https://example.com/character1.png",
        characterLocation = characterLocation,
        name = "Character Name",
        characterOrigin = characterOrigin,
        species = "Human",
        status = "Alive",
        type = "Main Character"
    )
}