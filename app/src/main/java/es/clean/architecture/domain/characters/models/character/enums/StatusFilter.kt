package es.clean.architecture.domain.characters.models.character.enums

enum class StatusFilter(val value: String) {
    NONE(""),
    ALIVE("alive"),
    DEAD("dead"),
    UNKNOWN("unknown"),
}
