package es.clean.architecture.domain.characters.models.character


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
data class RickyMortyCharacter(
    val info: Info,
    val characters: List<Character>
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
    data class Character(
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
fun createCharacterResult(): RickyMortyCharacter.Character {
    val characterLocation = RickyMortyCharacter.Character.CharacterLocation(
        name = "Earth",
        url = "https://example.com/earth"
    )
    val characterOrigin = RickyMortyCharacter.Character.CharacterOrigin(
        name = "Unknown",
        url = "https://example.com/unknown"
    )
    return RickyMortyCharacter.Character(
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