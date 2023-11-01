package es.clean.architecture.data.remote.characters.repository

import es.clean.architecture.models.CharacterModel

interface CharactersRepository {
    suspend fun getAllCharacters(page: Int): List<CharacterModel.CharacterResult>
}