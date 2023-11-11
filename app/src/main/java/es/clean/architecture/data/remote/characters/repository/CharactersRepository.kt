package es.clean.architecture.data.remote.characters.repository

import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel

interface CharactersRepository {
    suspend fun getAllCharacters(
        page: Int,
        name: String?
    ): List<RickyMortyCharacterModel.RickyMortyCharacter>
}