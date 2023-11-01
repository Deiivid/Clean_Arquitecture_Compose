package es.clean.architecture.domain.characters.repository.characters

import es.clean.architecture.domain.characters.models.character.RickyMortyCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(
        page: Int,
        ): Flow<List<RickyMortyCharacter.Character>>
}