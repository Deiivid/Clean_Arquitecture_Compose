package es.clean.architecture.domain.characters.models.character

data class SearchedCharacter(
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
)
