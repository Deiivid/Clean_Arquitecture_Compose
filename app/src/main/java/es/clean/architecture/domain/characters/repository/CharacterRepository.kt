package es.clean.architecture.domain.characters.repository

import es.clean.architecture.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<List<CharacterModel.CharacterResult>>
}