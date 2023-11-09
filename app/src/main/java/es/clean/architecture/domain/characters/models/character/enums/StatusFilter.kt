package es.clean.architecture.domain.characters.models.character.enums

enum class StatusFilter(val value: String) {
    none(""),
    alive("alive"),
    dead("dead"),
    unknown("unknown"),
}