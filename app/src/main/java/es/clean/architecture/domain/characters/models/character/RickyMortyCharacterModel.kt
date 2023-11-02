package es.clean.architecture.domain.characters.models.character


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
data class RickyMortyCharacterModel(
    val info: Info,
    val rickyMortyCharacters: List<RickyMortyCharacter>
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
    data class RickyMortyCharacter(
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


/**
 * We use this section to inicialize the detail screen to get the preview
 */
fun createCharacterResult(): RickyMortyCharacterModel.RickyMortyCharacter {
    val rickyMortyCharacterLocation = RickyMortyCharacterModel.RickyMortyCharacter.CharacterLocation(
        name = "Earth",
        url = "https://example.com/earth"
    )
    val rickyMortyCharacterOrigin = RickyMortyCharacterModel.RickyMortyCharacter.CharacterOrigin(
        name = "Unknown",
        url = "https://example.com/unknown"
    )
    return RickyMortyCharacterModel.RickyMortyCharacter(
        created = "2023-10-25",
        episode = listOf("Episode 1", "Episode 2"),
        gender = "Male",
        id = 1,
        image = "https://example.com/character1.png",
        characterLocation = rickyMortyCharacterLocation,
        name = "Character Name",
        characterOrigin = rickyMortyCharacterOrigin,
        species = "Human",
        status = "Alive",
        type = "Main Character"
    )
}