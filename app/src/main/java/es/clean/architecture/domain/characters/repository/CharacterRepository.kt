package es.clean.architecture.domain.characters.repository

import es.clean.architecture.models.RickyMortyCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<List<RickyMortyCharacter.Character>>
}