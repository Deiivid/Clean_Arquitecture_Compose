package es.clean.architecture.data.remote.characters.interfaces

import es.clean.architecture.models.CharacterModel


interface CharactersDataStore {
    suspend fun getAllCharacters(page: Int): List<CharacterModel.CharacterResult>
}